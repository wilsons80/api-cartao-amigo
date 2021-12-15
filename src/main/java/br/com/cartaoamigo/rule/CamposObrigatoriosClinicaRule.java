package br.com.cartaoamigo.rule;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.ClinicaTO;

@Component
public class CamposObrigatoriosClinicaRule {

	public void verificar(ClinicaTO to) {
		if(StringUtils.isEmpty(to.getEmail())) {
			throw new CamposObrigatoriosException("O e-mail deve ser informado.");
		}

		if(StringUtils.isEmpty(to.getCnpj()) && StringUtils.isEmpty(to.getCpf())) {
			throw new CamposObrigatoriosException("Informe o CNPJ ou CPF.");
		}

	}
}
