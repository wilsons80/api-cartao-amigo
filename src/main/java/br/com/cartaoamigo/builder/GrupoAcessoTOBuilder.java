package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.GrupoAcessoModulosRepository;
import br.com.cartaoamigo.entity.GrupoAcesso;
import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.to.GrupoAcessoTO;


@Component
public class GrupoAcessoTOBuilder {
	
	@Autowired private PerfilAcessoTOBuilder perfilAcessoTOBuilder; 
	@Autowired private GrupoAcessoModulosTOBuilder grupoAcessoModulosTOBuilder;
	@Autowired private GrupoAcessoModulosRepository grupoAcessoModulosRepository;
	
	public GrupoAcessoTO buildTO(GrupoAcesso p) {
		GrupoAcessoTO to = new GrupoAcessoTO();
		BeanUtils.copyProperties(p,  to);
		to.setPerfilAcesso(perfilAcessoTOBuilder.buildTO(p.getPerfilAcesso()));
		
		if(Objects.nonNull(p.getId())) {
			Optional<List<GrupoAcessoModulos>> entitys = grupoAcessoModulosRepository.findAllByGrupoAcesso(p.getId());
			if(entitys.isPresent()) {
				to.setGruposAcessoModulos(grupoAcessoModulosTOBuilder.buildAll(entitys.get()));
			}
		}
		
		return to;
	}

	public GrupoAcesso build(GrupoAcessoTO p) {
		GrupoAcesso to = new GrupoAcesso();
		if (Objects.isNull(p)) {
			return to;
		}
		BeanUtils.copyProperties(p,  to);
		
		to.setPerfilAcesso(perfilAcessoTOBuilder.build(p.getPerfilAcesso()));
		
		return to;
	}

	
	public List<GrupoAcesso> buildTOAll(List<GrupoAcessoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<GrupoAcessoTO> buildAll(List<GrupoAcesso> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
