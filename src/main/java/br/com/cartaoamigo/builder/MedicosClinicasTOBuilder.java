package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetClinicasCmd;
import br.com.cartaoamigo.cmd.GetMedicoCmd;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.MedicosClinicas;
import br.com.cartaoamigo.to.ClinicaTO;
import br.com.cartaoamigo.to.MedicoTO;
import br.com.cartaoamigo.to.MedicosClinicasTO;

@Component
public class MedicosClinicasTOBuilder {
		
	@Autowired private ClinicaTOBuilder clinicaTOBuilder;
	@Autowired private MedicoTOBuilder medicoTOBuilder;
	@Autowired private GetClinicasCmd clinicasCmd;
	@Autowired private GetMedicoCmd getMedicoCmd;
	
	public MedicosClinicasTO buildTO(MedicosClinicas p) {
		MedicosClinicasTO to = new MedicosClinicasTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setClinica(clinicaTOBuilder.buildTO(p.getClinica()));
		to.setMedico(medicoTOBuilder.buildTO(p.getMedico()));
		
		return to;
	}
	
	public MedicosClinicas build(MedicosClinicasTO p) {
		MedicosClinicas to = new MedicosClinicas();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		Optional.ofNullable(p.getClinica()).ifPresent(c -> {
			ClinicaTO clinicaTO = clinicasCmd.getById(p.getClinica().getId());
			to.setClinica(clinicaTOBuilder.build(clinicaTO));
		});
		
		Optional.ofNullable(p.getMedico()).ifPresent(c -> {
			MedicoTO medicoTO = getMedicoCmd.buscarPorId(p.getMedico().getId());
			to.setMedico(medicoTOBuilder.build(medicoTO));
		});
		
		return to;
	}
	
	public List<MedicosClinicas> buildTOAll(List<MedicosClinicasTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<MedicosClinicasTO> buildAll(List<MedicosClinicas> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
