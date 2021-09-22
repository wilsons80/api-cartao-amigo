package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoCargoTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoCargoRepository;
import br.com.cartaoamigo.entity.TipoCargo;
import br.com.cartaoamigo.to.TipoCargoTO;

@Component
public class CadastrarTipoCargoCmd {

	@Autowired private TipoCargoRepository repository;
	@Autowired private TipoCargoTOBuilder toBuilder;
	
	
	public TipoCargoTO cadastrar(TipoCargoTO to) {
		TipoCargo tipoCargoTO = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(tipoCargoTO));
	}
	
}
