package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.to.ClinicasTipoEspecialidadesTO;

@Component
public class ClinicasTipoEspecialidadesTOBuilder {
	
	@Autowired private ClinicaTOBuilder clinicaTOBuilder;
	@Autowired private TipoEspecialidadeTOBuilder especialidadeTOBuilder;
	
	public ClinicasTipoEspecialidadesTO buildTO(ClinicasTipoEspecialidades p) {
		ClinicasTipoEspecialidadesTO to = new ClinicasTipoEspecialidadesTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		Optional.ofNullable(p.getClinica()).ifPresent( clinica -> {
			to.setClinica(clinicaTOBuilder.buildTO(clinica));
		});
		
		Optional.ofNullable(p.getTipoEspecialidade()).ifPresent( tipoEspecialidade -> {
			to.setTipoEspecialidade(especialidadeTOBuilder.buildTO(tipoEspecialidade));
		});
		
		return to;
	}
	
	public ClinicasTipoEspecialidades build(ClinicasTipoEspecialidadesTO p) {
		ClinicasTipoEspecialidades to = new ClinicasTipoEspecialidades();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		
		to.setClinica(clinicaTOBuilder.build(p.getClinica()));
		to.setTipoEspecialidade(especialidadeTOBuilder.build(p.getTipoEspecialidade()));
		
		return to;
	}
	
	public List<ClinicasTipoEspecialidades> buildTOAll(List<ClinicasTipoEspecialidadesTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<ClinicasTipoEspecialidadesTO> buildAll(List<ClinicasTipoEspecialidades> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
		
	}

}
