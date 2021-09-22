package br.com.cartaoamigo.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.ArquivoTO;

@Component
public class CamposObrigatoriosArquivoRule {

	public void verificar(ArquivoTO to) {

		if (StringUtils.isEmpty(to.getUrl())) {
			throw new CamposObrigatoriosException("URL deve ser informada.");
		}
		

	}
}
