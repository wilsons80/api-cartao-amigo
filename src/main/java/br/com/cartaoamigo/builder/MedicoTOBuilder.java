package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.MedicoEspecialidadesRepository;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.Medico;
import br.com.cartaoamigo.entity.MedicoEspecialidades;
import br.com.cartaoamigo.to.MedicoTO;

@Component
public class MedicoTOBuilder {
	
	@Autowired
	private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	
	@Autowired private MedicoEspecialidadesRepository medicoEspecialidadesRepository;
	@Autowired private MedicoEspecialidadesTOBuilder medicoEspecialidadesTOBuilder;
	
	public MedicoTO buildTO(Medico p) {
		MedicoTO to = new MedicoTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		
		Optional<List<MedicoEspecialidades>> especialidades = medicoEspecialidadesRepository.findAllByMedico(p.getId());
		if(especialidades.isPresent()) {
			to.setEspecialidades( medicoEspecialidadesTOBuilder.buildAll( especialidades.get() ));
		}
		
		return to;
	}
	
	public Medico build(MedicoTO p) {
		Medico to = new Medico();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);

		to.setPessoaFisica(pessoaFisicaTOBuilder.build(p.getPessoaFisica()));		
		
		return to;
	}
	
	public List<Medico> buildTOAll(List<MedicoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<MedicoTO> buildAll(List<Medico> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
