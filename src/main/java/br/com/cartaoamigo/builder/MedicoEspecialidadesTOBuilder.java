package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.Medico;
import br.com.cartaoamigo.entity.MedicoEspecialidades;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;

@Component
public class MedicoEspecialidadesTOBuilder {
	
	@Autowired private TipoEspecialidadeTOBuilder tipoEspecialidadeTOBuilder;
	
	public MedicoEspecialidadesTO buildTO(MedicoEspecialidades p) {
		MedicoEspecialidadesTO to = new MedicoEspecialidadesTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setTipoEspecialidade(tipoEspecialidadeTOBuilder.buildTO(p.getTipoEspecialidade()));
		
		return to;
		
	}
	
	public MedicoEspecialidades build(MedicoEspecialidadesTO p) {
		MedicoEspecialidades to = new MedicoEspecialidades();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setTipoEspecialidade(tipoEspecialidadeTOBuilder.build(p.getTipoEspecialidade()));
		
		return to;
	}
	
	public MedicoEspecialidades build(MedicoEspecialidadesTO p, Medico medico) {
		MedicoEspecialidades to = new MedicoEspecialidades();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setTipoEspecialidade(tipoEspecialidadeTOBuilder.build(p.getTipoEspecialidade()));
		
		return to;
	}
	
	public List<MedicoEspecialidades> buildTOAll(List<MedicoEspecialidadesTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
		
	}
	
	public List<MedicoEspecialidadesTO> buildAll(List<MedicoEspecialidades> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	
	}
}
