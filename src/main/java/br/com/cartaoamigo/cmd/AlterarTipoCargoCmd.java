package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoCargoTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoCargoRepository;
import br.com.cartaoamigo.entity.TipoCargo;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.TipoCargoTO;

@Component
public class AlterarTipoCargoCmd {

	@Autowired private TipoCargoRepository repository;
	@Autowired private TipoCargoTOBuilder toBuilder;

	public TipoCargoTO alterar(TipoCargoTO to) {
		TipoCargo entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Tipo de cargo informado não existe: " + to.getId()));
		entity = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(entity));
	}

}
