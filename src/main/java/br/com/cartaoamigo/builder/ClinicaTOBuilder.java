package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.to.ClinicaTO;

@Component
public class ClinicaTOBuilder {
	
	@Autowired private ArquivosMetadadosTOBuilder metadadosTOBuilder;
	
	public ClinicaTO buildTO(Clinica p) {
		ClinicaTO to = new ClinicaTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		Optional.ofNullable(p.getMetadados()).ifPresent( metadados -> {
			to.setMetadados(metadadosTOBuilder.buildTO(metadados));
		});
		
		return to;
	}
	
	public Clinica build(ClinicaTO p) {
		Clinica to = new Clinica();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setMetadados(metadadosTOBuilder.build(p.getMetadados()));
		
		return to;
	}
	
	public List<Clinica> buildTOAll(List<ClinicaTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<ClinicaTO> buildAll(List<Clinica> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
		
	}

}
