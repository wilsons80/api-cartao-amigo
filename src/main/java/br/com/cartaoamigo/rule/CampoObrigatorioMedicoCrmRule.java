package br.com.cartaoamigo.rule;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.MedicoTO;

@Component
public class CampoObrigatorioMedicoCrmRule {

	public void verificar(MedicoTO to) {
		if(StringUtils.isEmpty(to.getCrm())) {
			throw new CamposObrigatoriosException("Crm  deve ser informado.");
		}

	}
}
