package br.com.cartaoamigo.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PerfilAmbienteSistemaTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAmbienteSistemaRepository;
import br.com.cartaoamigo.entity.PerfilAmbienteSistema;
import br.com.cartaoamigo.to.PerfilAmbienteSistemaTO;

@Component
public class GetPerfilAmbienteSistemaCmd {
	
	@Autowired private PerfilAmbienteSistemaRepository repository;
	@Autowired private PerfilAmbienteSistemaTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	
	public PerfilAmbienteSistemaTO get() {
		Long idUsuario = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
		Optional<PerfilAmbienteSistema> entityOptional = repository.findByIdUsuario(idUsuario);
		if(!entityOptional.isPresent()) {
			return null;
		}
		return toBuilder.buildTO(entityOptional.get());
	}
	
	
	public Optional<PerfilAmbienteSistema> getPerfil() {
		Long idUsuario = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
		return repository.findByIdUsuario(idUsuario);
	}
	
}
