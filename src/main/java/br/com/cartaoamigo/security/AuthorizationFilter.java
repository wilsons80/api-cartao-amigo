package br.com.cartaoamigo.security;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cartaoamigo.exception.TokenInvalidoException;
import br.com.cartaoamigo.infra.constantes.SecurityContantes;
import br.com.cartaoamigo.service.exception.ApiError;
import io.jsonwebtoken.Claims;

@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthorizationFilter extends OncePerRequestFilter {
	
	@SuppressWarnings("deprecation")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt              = request.getHeader(HttpHeaders.AUTHORIZATION);
		String idsessionusuario = request.getHeader(SecurityContantes.IDSESSIONUSUARIO);
		
		try {
			if(jwt == null || !jwt.startsWith(SecurityContantes.JWT_PROVIDER)) {
				
				throw new TokenInvalidoException("Token ausente na requisição.");
				
			} else {
				
				jwt = jwt.replace(SecurityContantes.JWT_PROVIDER, "");
				
				//Valida o token informado
				Claims claims = new JwtManager().validaToken(jwt);
				if( claims == null ) {
					throw new TokenInvalidoException("Token de acesso inválido, faça o login novamente.");
				}
				
				String username = claims.getSubject();
				
				@SuppressWarnings("unchecked")
				List<String> roles = (List<String>) claims.get(SecurityContantes.JWT_ROLE_KEY);
				
				List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
				roles.forEach(role -> {
					grantedAuthorities.add(new SimpleGrantedAuthority(role));
				});
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(username +"@@"+idsessionusuario, null, grantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch (Exception e) {
			ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(), SecurityContantes.JWT_INVALID_MSG);
			PrintWriter writer = response.getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			String apiErrorString = mapper.writeValueAsString(apiError);
			
			writer.write(apiErrorString);
			
			response.setContentType(APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			
			return;
		}
		
		filterChain.doFilter(request, response);
		
	}

}
