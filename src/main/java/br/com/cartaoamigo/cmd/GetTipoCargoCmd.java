package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoCargoTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoCargoRepository;
import br.com.cartaoamigo.entity.TipoCargo;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.TipoCargoTO;

@Component
public class GetTipoCargoCmd {

	@Autowired private TipoCargoRepository repository;
	@Autowired private TipoCargoTOBuilder toBuilder;

	public TipoCargoTO getTOById(Long id) {
		TipoCargo avaliacao = getById(id);
		return toBuilder.buildTO(avaliacao);
	}

	public TipoCargo getById(Long id) {
		TipoCargo arquivo = repository.findById(id).orElseThrow(() -> new NotFoundException("Tipo de cargo não existe: " + id));
		return arquivo;
	}


	public List<TipoCargoTO> getAll() {
		List<TipoCargo> tipos = repository.findAll();
		return toBuilder.buildAll(tipos);
	}

}
