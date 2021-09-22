package br.com.cartaoamigo.rule;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.base.NegocioException;

@Component
public class CepInvalidoRule {

	public void validar(String cep) {
		if(cep == null || cep.equals("")) {
			throw new NegocioException("CEP não informado.");
		}
		
		cep = cep.replaceAll("\\D", "");
		if(cep.length() != 8) {
			throw new NegocioException("CEP inválido.");
		}
	}

}
