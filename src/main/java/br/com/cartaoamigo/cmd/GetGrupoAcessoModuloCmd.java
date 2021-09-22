package br.com.cartaoamigo.cmd;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoModulosTOBuilder;
import br.com.cartaoamigo.dao.repository.GrupoAcessoModulosRepository;
import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.to.GrupoAcessoModulosTO;

@Component
public class GetGrupoAcessoModuloCmd {

	@Autowired private GrupoAcessoModulosRepository repository;
	@Autowired private GrupoAcessoModulosTOBuilder toBuilder;

	
	public List<GrupoAcessoModulosTO> getModuloPai(Long idGrupoAcesso, Long idModuloPai) {
		Optional<List<GrupoAcessoModulos>> entity = repository.findModuloPai(idGrupoAcesso, idModuloPai);
		if(entity.isPresent()) {
			return toBuilder.buildAll(entity.get());
		}
		return null;
	}
	
}
