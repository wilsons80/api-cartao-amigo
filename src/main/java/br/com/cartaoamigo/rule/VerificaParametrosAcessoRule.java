package br.com.cartaoamigo.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class VerificaParametrosAcessoRule {

	public void verificar(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new ParametroNaoInformadoException("Parâmetro username não informado");
		}
	}

}
