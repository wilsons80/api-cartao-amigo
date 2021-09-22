package br.com.cartaoamigo.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.to.UnidadeTO;

@Component
public class ValidarCadastroUnidadeRule {
	
	
	public void validar(UnidadeTO to) {

		if(StringUtils.isEmpty(to.getCnpj())) {
			throw new ParametroNaoInformadoException("CNPJ da unidade não foi informado.");
		}
		if(StringUtils.isEmpty(to.getCodigoUnidade())) {
			throw new ParametroNaoInformadoException("A sigla da unidade não foi informada.");
		}
		if(StringUtils.isEmpty(to.getNomeUnidade())) {
			throw new ParametroNaoInformadoException("O nomeda da unidade não foi informada.");
		}
		
	}

}
