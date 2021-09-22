package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ParametrosTOBuilder;
import br.com.cartaoamigo.dao.repository.ParametrosRepository;
import br.com.cartaoamigo.entity.Parametros;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.ParametrosTO;

@Component
public class GetParametrosCmd {

	@Autowired private ParametrosRepository repository;
	@Autowired private ParametrosTOBuilder toBuilder;
	
	public List<ParametrosTO> getAll() {
		List<Parametros> entitys = repository.findAll();
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<ParametrosTO>();
		}
		return toBuilder.buildAll(entitys);
	}
	
	public ParametrosTO getById(Long id) {
		Optional<Parametros> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Parametro não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	
	public ParametrosTO getByCodigo(String codigo) {
		Optional<Parametros> entityOptional = repository.findByCodigo(codigo);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Parametro não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

}
