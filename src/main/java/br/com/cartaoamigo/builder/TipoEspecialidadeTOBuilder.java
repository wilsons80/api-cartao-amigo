package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.TipoEspecialidade;
import br.com.cartaoamigo.to.TipoEspecialidadeTO;

@Component
public class TipoEspecialidadeTOBuilder {

	
	public TipoEspecialidadeTO buildTO(TipoEspecialidade p) {
		TipoEspecialidadeTO to = new TipoEspecialidadeTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		return to;
	}
	
	public TipoEspecialidade build(TipoEspecialidadeTO p) {
		TipoEspecialidade to = new TipoEspecialidade();
		
		if (Objects.isNull(p)) {
			return to;
		}
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		return to;
	}
	
	public List<TipoEspecialidade> buildTOAll(List<TipoEspecialidadeTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
		
	}
	
	public List<TipoEspecialidadeTO> buildAll(List<TipoEspecialidade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
}
