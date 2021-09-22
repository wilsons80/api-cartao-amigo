package br.com.cartaoamigo.cmd;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.security.JwtManager;

@Component
public class TokenJwtCmd {
	
	@Autowired private JwtManager jwtManager;
	
	public String createToken( String username, Collection<? extends GrantedAuthority> authorities) {
		List<String> roles = authorities.stream()
				                        .map(authority -> authority.getAuthority())
				                        .collect(Collectors.toList());

		        
		String jwt = jwtManager.createToken(username, roles);
		
		return jwt;
	}

}
