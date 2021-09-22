package br.com.cartaoamigo.rule;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.TipoEspecialidadeTO;

@Component
public class CampoObrigatorioTipoEspecialiadadeRule {
	
	public void verificar(TipoEspecialidadeTO to) {
		if (StringUtils.isEmpty(to.getDescricao())) {
			throw new CamposObrigatoriosException("Descrição  deve ser informada.");
		}
		
	}

}
