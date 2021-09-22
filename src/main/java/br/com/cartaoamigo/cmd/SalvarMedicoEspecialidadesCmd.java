package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicoEspecialidadesTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicoEspecialidadesRepository;
import br.com.cartaoamigo.dao.repository.MedicosRepository;
import br.com.cartaoamigo.entity.Medico;
import br.com.cartaoamigo.entity.MedicoEspecialidades;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.rule.CamposObrigatoriosMedicoEspecialidadeRule;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;

@Component
public class SalvarMedicoEspecialidadesCmd  {

	@Autowired private MedicoEspecialidadesRepository repository;
	@Autowired private MedicoEspecialidadesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMedicoEspecialidadeRule rule;
	@Autowired private AlterarListaMedicoEspecialidadeCmd alterarCmd;
	@Autowired private MedicosRepository medicosRepository;
	
	public MedicoEspecialidadesTO cadastrar(MedicoEspecialidadesTO to, Medico medico) {
		MedicoEspecialidades entity = toBuilder.build(to, medico);
		rule.verificar(to);
		return toBuilder.buildTO(repository.save(entity));
	}
	
	
	public List<MedicoEspecialidadesTO> salvarAll(List<MedicoEspecialidadesTO> listaTO, Long idMedico) {
		Optional<Medico> medico = medicosRepository.findById(idMedico);
		if(!medico.isPresent()) {
			throw new NotFoundException("O médico informado não existe.");
		}
		
		alterarCmd.alterarAll(listaTO, medico.get());		
		
		List<MedicoEspecialidades> medicosEspecialidadesTO = repository.findAllByMedico(idMedico).orElse(new ArrayList<>());
		return toBuilder.buildAll(medicosEspecialidadesTO);
	}
	

	
}

