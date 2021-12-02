package br.com.cartaoamigo.security;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetTimeTokenExpiredCmd;
import br.com.cartaoamigo.infra.constantes.SecurityContantes;
import br.com.cartaoamigo.infra.util.Java8DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
	
	@Autowired private GetTimeTokenExpiredCmd getTimeTokenExpiredCmd;

	public String createToken(String username, List<String> roles) {
		String key = SecurityContantes.API_KEY;
		String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
		byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Key);
		
		JwtBuilder token = Jwts.builder()
  							   .setSubject(username)
							   .claim(SecurityContantes.JWT_ROLE_KEY, roles)
							   .setIssuedAt(new Date())
							   .signWith(SignatureAlgorithm.HS512, secretBytes);
		
		Integer minutes = getTimeTokenExpiredCmd.getTimeExpieredToken();
		if( Objects.nonNull(minutes)  && minutes > 0 ) {
			LocalDateTime expired = LocalDateTime.now().plusMinutes(minutes);
			token.setExpiration(Java8DateUtil.getDate(expired));
		}
		
		return token.compact();
	}
	
	public Claims validaToken(String jwt) throws JwtException {
		Claims claims = Jwts.parser()
							.setSigningKey(SecurityContantes.API_KEY.getBytes())
							.parseClaimsJws(jwt)
							.getBody();
		
		
		return claims;
	}
	
}
