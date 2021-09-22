package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.TipoUsuario;
import br.com.cartaoamigo.to.TipoUsuarioTO;

@Component
public class TipoUsuarioTOBuilder {
	
	
	public TipoUsuarioTO buildTO(TipoUsuario p) {
		TipoUsuarioTO to = new TipoUsuarioTO();
		
		if(Objects.isNull(p)) {return to;}
		
		BeanUtils.copyProperties(p, to);
		return to;
	}
	
	public TipoUsuario build(TipoUsuarioTO p) {
		TipoUsuario to = new TipoUsuario();
		BeanUtils.copyProperties(p, to);
		return to;
	}
	
	public List<TipoUsuario> buildTOAll(List<TipoUsuarioTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<TipoUsuarioTO> buildAll(List<TipoUsuario> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
		
	}

}
