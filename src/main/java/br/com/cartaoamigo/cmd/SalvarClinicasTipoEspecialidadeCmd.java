package br.com.cartaoamigo.cmd;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicasTipoEspecialidadesTOBuilder;
import br.com.cartaoamigo.dao.repository.ClinicaRepository;
import br.com.cartaoamigo.dao.repository.ClinicasTipoEspecialidadesRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.to.ClinicasTipoEspecialidadesTO;

@Component
public class SalvarClinicasTipoEspecialidadeCmd {

	@Autowired private AlterarListaClinicasTipoEspecialidadeCmd alterarListaCmd;
	@Autowired private ClinicaRepository clinicaRepository;
	
	@Autowired private ClinicasTipoEspecialidadesRepository repository;
	@Autowired private ClinicasTipoEspecialidadesTOBuilder toBuilder;
	
	public List<ClinicasTipoEspecialidadesTO> salvar(Long idClinica, List<ClinicasTipoEspecialidadesTO> listaTO) {
		Optional<Clinica> clinica = clinicaRepository.findById(idClinica);
		listaTO = Objects.isNull(listaTO) ? new ArrayList<ClinicasTipoEspecialidadesTO>() : listaTO; 
		
		alterarListaCmd.alterarAll(listaTO, clinica.get());
		
		Optional<List<ClinicasTipoEspecialidades>> entitys = repository.findAllByClinica(idClinica);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<ClinicasTipoEspecialidadesTO>();
	}
}
