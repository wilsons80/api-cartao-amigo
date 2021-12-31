package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.ws.pagarme.to.ClientePagarMeTO;

@Component
public class CamposObrigatoriosClientePagarMeRule {

	public void verificar(ClientePagarMeTO to) {
		if(Objects.isNull(to.getEmail())) {
			throw new CamposObrigatoriosException("O email deve ser informado.");
		}
		
		if(Objects.isNull(to.getDocument())) {
			throw new CamposObrigatoriosException("O documento deve ser informado.");
		}

	}
}
