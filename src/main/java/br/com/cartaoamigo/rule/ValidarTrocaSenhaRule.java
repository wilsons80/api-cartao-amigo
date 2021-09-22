package br.com.cartaoamigo.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.TrocaSenhaInvalidaException;
import br.com.cartaoamigo.security.CustomPasswordEncoder;

@Component
public class ValidarTrocaSenhaRule {
	
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	
	public void validar(String senhaBD, String senhaAtual, String senhaNova) {
		if(StringUtils.isEmpty(senhaNova)) {
			throw new NotFoundException("A nova senha não foi informada.");
		}
		if(StringUtils.isEmpty(senhaAtual)) {
			throw new NotFoundException("A senha atual não foi informada.");
		}
		
		String senhaAtualEncoder = customPasswordEncoder.encode(senhaAtual);
		if(!senhaBD.equals(senhaAtualEncoder)) {
			throw new TrocaSenhaInvalidaException("A senha atual informada não confere.");
		}
		
	}

}
