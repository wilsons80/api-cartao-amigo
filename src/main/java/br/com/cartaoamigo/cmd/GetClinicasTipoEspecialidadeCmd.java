package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicasTipoEspecialidadesTOBuilder;
import br.com.cartaoamigo.dao.repository.ClinicasTipoEspecialidadesRepository;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.to.ClinicasTipoEspecialidadesTO;

@Component
public class GetClinicasTipoEspecialidadeCmd {

	@Autowired private ClinicasTipoEspecialidadesRepository repository;
	@Autowired private ClinicasTipoEspecialidadesTOBuilder toBuilder;
	
	
	public ClinicasTipoEspecialidadesTO getById(Long idClinicaTipoEspecialidade) {
		Optional<ClinicasTipoEspecialidades> entity = repository.findById(idClinicaTipoEspecialidade);
		if(!entity.isPresent()) {return null;}
		return toBuilder.buildTO(entity.get());
	}
	
	public List<ClinicasTipoEspecialidadesTO> getAll() {
		Optional<List<ClinicasTipoEspecialidades>> entitys = Optional.ofNullable(repository.findAll());
		if(!entitys.isPresent()) {return null;}
		return toBuilder.buildAll(entitys.get());
	}
	
	
	public List<ClinicasTipoEspecialidadesTO> getAllByClinica(Long idClinica) {
		Optional<List<ClinicasTipoEspecialidades>> entitys = repository.findAllByClinica(idClinica);
		if(!entitys.isPresent()) {return null;}
		return toBuilder.buildAll(entitys.get());
	}
	
	
	public List<ClinicasTipoEspecialidadesTO> findAllByTipoEspecialidade(Long idTipoEspecialidade){
		Optional<List<ClinicasTipoEspecialidades>> entitys = repository.findAllByTipoEspecialidade(idTipoEspecialidade);
		if(!entitys.isPresent()) {return null;}
		return toBuilder.buildAll(entitys.get());
	}
	
	
	public ClinicasTipoEspecialidadesTO getTOByClinicaAndTipoEspecialidade(Long idClinica, Long idTipoEspecialidade) {
		Optional<ClinicasTipoEspecialidades> entity = getByClinicaAndTipoEspecialidade(idClinica, idTipoEspecialidade);
		if(!entity.isPresent()) {return null;}
		return toBuilder.buildTO(entity.get());
	}
	
	public Optional<ClinicasTipoEspecialidades> getByClinicaAndTipoEspecialidade(Long idClinica, Long idTipoEspecialidade) {
		Optional<ClinicasTipoEspecialidades> entity = repository.findByClinicaAndTipoEspecialidade(idClinica, idTipoEspecialidade);
		if(!entity.isPresent()) {return Optional.empty();}
		return entity;
	}
	
	
}
