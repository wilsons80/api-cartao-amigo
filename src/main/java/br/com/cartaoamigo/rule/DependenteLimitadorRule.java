package br.com.cartaoamigo.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.QuantidadesDependentesInvalidException;
import br.com.cartaoamigo.to.DependentesTitularTO;

@Component
public class DependenteLimitadorRule {
		
	public void validar(List<DependentesTitularTO> dependentes) {
		if(dependentes != null && dependentes.size() > 4) {
			throw new QuantidadesDependentesInvalidException("Permitido apenas quatro dependentes.");
		}
	}
	
	
}
