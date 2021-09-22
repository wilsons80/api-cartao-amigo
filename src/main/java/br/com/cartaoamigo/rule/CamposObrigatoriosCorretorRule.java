package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.CorretorTO;

@Component
public class CamposObrigatoriosCorretorRule {

	public void verificar(CorretorTO to) {
		if(Objects.isNull(to.getAtivo())) {
			throw new CamposObrigatoriosException("O status deve ser informado.");
		}
		
		if(Objects.isNull(to.getPessoaFisica())) {
			throw new CamposObrigatoriosException("Pessoa física deve ser informada.");
		}

		if(Objects.isNull(to.getIsPorcentagem())) {
			throw new CamposObrigatoriosException("O tipo de porcentagem  deve ser informado.");
		}

		if(Objects.isNull(to.getValorRecebimento())) {
			throw new CamposObrigatoriosException("O valor de recebimento deve ser informado.");
		}

	}
}
