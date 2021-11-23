package br.com.cartaoamigo.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CpfDuplicadoException;
import br.com.cartaoamigo.infra.util.Java8StreamUtil;

@Component
public class ValidarDependentesCPFDuplicadosRule {

	public void validar(List<String> listaCPF) {
		if(Java8StreamUtil.isValoresDuplicados(listaCPF)) {
			throw new CpfDuplicadoException("Foi detectado a presença de CPFs repetidos na lista de dependentes.");
		}
	}
}
