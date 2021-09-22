package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.enums.Estados;

@Component
public class GetEstadosCmd {

	public List<String> getAllEstados() {
		return Stream.of(Estados.values())
				.map(Estados::getSigla)
				.collect(Collectors.toList());
	}
}
