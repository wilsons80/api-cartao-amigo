package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PerfilAmbienteSistemaTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAmbienteSistemaRepository;
import br.com.cartaoamigo.entity.PerfilAmbienteSistema;
import br.com.cartaoamigo.to.PerfilAmbienteSistemaTO;

@Component
public class SalvarPerfilAmbienteSistemaCmd {
	
	@Autowired private PerfilAmbienteSistemaRepository repository;
	@Autowired private PerfilAmbienteSistemaTOBuilder toBuilder;
	@Autowired private GetPerfilAmbienteSistemaCmd getPerfilAmbienteSistemaCmd;
	
	
	public PerfilAmbienteSistemaTO salvar(PerfilAmbienteSistemaTO to) {
		PerfilAmbienteSistema entity = getPerfilAmbienteSistemaCmd.getPerfil().orElse(new PerfilAmbienteSistema());
		toBuilder.buildEntity(to, entity);		
		return toBuilder.buildTO(repository.save(entity));
	}



}
