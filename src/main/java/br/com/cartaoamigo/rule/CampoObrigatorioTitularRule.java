package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.TitularTO;

@Component
public class CampoObrigatorioTitularRule {
 
	public void verificar(TitularTO to) {
		
		if (Objects.isNull(to.getId())) {
			throw new CamposObrigatoriosException("Pessoa Física deve ser informada");
		}
	}
	
}
