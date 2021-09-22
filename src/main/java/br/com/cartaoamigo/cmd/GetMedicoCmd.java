package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicoTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicosRepository;
import br.com.cartaoamigo.to.MedicoTO;

@Component
public class GetMedicoCmd {
	
	@Autowired private MedicosRepository repository;
	@Autowired private MedicoTOBuilder tobuilder;
	
	public List<MedicoTO> buscarTodos() {
		return tobuilder.buildAll(repository.findAll());
	}
	
	public MedicoTO buscarPorId(Long id) {
		return tobuilder.buildTO(repository.findById(id).orElse(null));
	}

}
