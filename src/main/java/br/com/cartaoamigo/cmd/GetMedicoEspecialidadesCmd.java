package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicoEspecialidadesTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicoEspecialidadesRepository;
import br.com.cartaoamigo.entity.MedicoEspecialidades;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;

@Component
public class GetMedicoEspecialidadesCmd {
	
	@Autowired MedicoEspecialidadesRepository repository;
	@Autowired MedicoEspecialidadesTOBuilder toBuilder;
	
	public List<MedicoEspecialidadesTO> buscarTodos() {
		return toBuilder.buildAll(repository.findAll());
	}
	
	public MedicoEspecialidadesTO buscarPorId(Long idMedicoEspecialidades) {
		Optional<MedicoEspecialidades> entity = repository.findById(idMedicoEspecialidades);
		return toBuilder.buildTO(entity.get());
	}
	
	
}
