package br.com.cartaoamigo.cmd;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoTOBuilder;
import br.com.cartaoamigo.dao.repository.GrupoAcessoRepository;
import br.com.cartaoamigo.entity.GrupoAcesso;
import br.com.cartaoamigo.to.GrupoAcessoTO;

@Component
public class GetGrupoAcessoCmd {

	@Autowired private GrupoAcessoRepository repository;
	@Autowired private GrupoAcessoTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public List<GrupoAcessoTO> getAllGruposAdministrativos() {
		Optional<List<GrupoAcesso>> entitys = Optional.empty();
		
		if(getUsuarioLogadoCmd.isUsuarioLogodoRoot() ) {
			entitys = repository.findAllGruposParaUsuariosRoot();
		} else {
			entitys = repository.findAllGruposParaUsuariosAdministrativos();
		}
		
		if(!entitys.isPresent()) {return null;}
		return toBuilder.buildAll(entitys.get());
	}
	
	public GrupoAcessoTO getById(Long idGrupoAcesso) {
		Optional<GrupoAcesso> entity = repository.findById(idGrupoAcesso);
		return builder(entity);
	}

	public GrupoAcessoTO getGrupoClinica() {
		Optional<GrupoAcesso> entity = repository.findGruposClinicas();
		return builder(entity);
	}

	
	public GrupoAcessoTO getGrupoAssociadoTitular() {
		Optional<GrupoAcesso> entity = repository.findGruposAssociadoTitular();
		return builder(entity);
	}

	public GrupoAcessoTO getGrupoAssociadoDependente() {
		Optional<GrupoAcesso> entity = repository.findGruposAssociadoDependente();
		return builder(entity);
	}

	
	private GrupoAcessoTO builder(Optional<GrupoAcesso> entity) {
		if(!entity.isPresent()) {return null;}
		return toBuilder.buildTO(entity.get());
	}
	
	public List<GrupoAcessoTO> getAllFilter(Long idModulo, String nome) {

		Optional<List<GrupoAcesso>> entitys = Optional.empty();

		idModulo              = Objects.isNull(idModulo) ? null : idModulo;
		nome 				  = StringUtils.isEmpty(nome) ? null : nome;
		
		entitys = repository.findByFilter(idModulo, nome, getUsuarioLogadoCmd.isUsuarioLogodoRoot() );
		if (entitys.isPresent()) {
			List<GrupoAcessoTO> grupoAcesso = toBuilder.buildAll(entitys.get());
			return grupoAcesso;
		}
		
		return new ArrayList<GrupoAcessoTO>();
	}
	
}
