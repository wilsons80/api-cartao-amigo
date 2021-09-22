package br.com.cartaoamigo.rule;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;

@Component
public class ValidarDadosCartaoCRPagSeguroRule {

	public void validar(String nomeImpresso, String cpfTitular, LocalDate dataNascimentoTitularCartao) {
		if(StringUtils.isEmpty(nomeImpresso)) {
			throw new CamposObrigatoriosException("O nome impresso deve ser informado.");
		}
		
		if(StringUtils.isEmpty(cpfTitular)) {
			throw new CamposObrigatoriosException("O CPF do titular do cartão deve ser informado.");
		}

		if(Objects.isNull(dataNascimentoTitularCartao)) {
			throw new CamposObrigatoriosException("A Data de nascimento do titular do cartão deve ser informada.");
		}
	}
}
