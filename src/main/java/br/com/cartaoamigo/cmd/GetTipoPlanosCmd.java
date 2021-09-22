package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoPlanoTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoPlanoRepository;
import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.to.TipoPlanoTO;

@Component
public class GetTipoPlanosCmd {
	
	@Autowired private TipoPlanoRepository repository;
	@Autowired private TipoPlanoTOBuilder toBuilder;
	
	public TipoPlanoTO findByIdTipoPlano(Long idTipoPlano) {
		Optional<TipoPlano> entity = repository.findById(idTipoPlano);
		return toBuilder.buildTO(entity.get());
	}
	
	public List<TipoPlanoTO> getAll() {
		Optional<List<TipoPlano>> entitys = Optional.ofNullable(repository.findAll());
		return toBuilder.buildAll(entitys.get());
	}
	
	public List<TipoPlanoTO> findAllAtivos() {
		Optional<List<TipoPlano>> entitys = repository.findAllAtivos();
		return toBuilder.buildAll(entitys.get());
	}

}
