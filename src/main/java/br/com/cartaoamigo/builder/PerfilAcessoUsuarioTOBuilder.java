package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;


@Component
public class PerfilAcessoUsuarioTOBuilder {
	
	@Autowired private GrupoAcessoTOBuilder grupoAcessoTOBuilder; 
	
	public PerfilAcessoUsuarioTO buildTO(PerfilAcessoUsuario entity) {
		PerfilAcessoUsuarioTO to = new PerfilAcessoUsuarioTO();
		
		if (Objects.isNull(entity)) {return null;}
		
		BeanUtils.copyProperties(entity,  to);
		
		to.setIdUsuario(entity.getIdUsuario());
		to.setGrupoAcesso(grupoAcessoTOBuilder.buildTO(entity.getGrupoAcesso()));
	
		return to;
	}

	
	public PerfilAcessoUsuario build(PerfilAcessoUsuarioTO to) {
		PerfilAcessoUsuario retorno = new PerfilAcessoUsuario();
		
		BeanUtils.copyProperties(to,  retorno);
		
		retorno.setIdUsuario(to.getIdUsuario());
		retorno.setGrupoAcesso(grupoAcessoTOBuilder.build(to.getGrupoAcesso()));
		
		return retorno;
	}

	
	public List<PerfilAcessoUsuarioTO> buildAll(List<PerfilAcessoUsuario> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<PerfilAcessoUsuario> buildAllTO(List<PerfilAcessoUsuarioTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}

}
