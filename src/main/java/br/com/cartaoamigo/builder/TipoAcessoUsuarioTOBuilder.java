package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.to.TipoAcessoUsuarioTO;


@Component
public class TipoAcessoUsuarioTOBuilder {
	
	
	public TipoAcessoUsuario build(TipoAcessoUsuarioTO p) {
		TipoAcessoUsuario retorno = new TipoAcessoUsuario();
		BeanUtils.copyProperties(p,  retorno);
		return retorno;
	}

	public TipoAcessoUsuarioTO buildTO(TipoAcessoUsuario p) {
		TipoAcessoUsuarioTO retorno = new TipoAcessoUsuarioTO();
		BeanUtils.copyProperties(p,  retorno);
		return retorno;
	}
	

	public List<TipoAcessoUsuarioTO> buildAll(List<TipoAcessoUsuario> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
