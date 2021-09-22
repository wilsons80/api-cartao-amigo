package br.com.cartaoamigo.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.TrocaSenhaInvalidaException;
import br.com.cartaoamigo.security.CustomPasswordEncoder;

@Component
public class ValidarSenhaInformadaRule {
	
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	
	public void validar(String senha1, String senha2) {
		if(StringUtils.isEmpty(senha1)) {
			throw new NotFoundException("A nova senha não foi informada.");
		}
		if(StringUtils.isEmpty(senha2)) {
			throw new NotFoundException("A senha de confirmação não foi informada.");
		}
		
		String senha1Encoder = customPasswordEncoder.encode(senha1);
		String senha2Encoder = customPasswordEncoder.encode(senha2);
		if(!senha1Encoder.equals(senha2Encoder)) {
			throw new TrocaSenhaInvalidaException("As senhas informadas não confere.");
		}
		
	}

}
