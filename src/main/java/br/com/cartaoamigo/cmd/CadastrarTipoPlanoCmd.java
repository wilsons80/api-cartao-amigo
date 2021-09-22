package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoPlanoTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoPlanoRepository;
import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.to.TipoPlanoTO;

@Component
public class CadastrarTipoPlanoCmd {

	@Autowired private TipoPlanoRepository repository;
	@Autowired private TipoPlanoTOBuilder toBuilder;
	
	
	public TipoPlanoTO cadastrar(TipoPlanoTO to) {
		TipoPlano tipoPlanoTO = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(tipoPlanoTO));
	}
	
}
