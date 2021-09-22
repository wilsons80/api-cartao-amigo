package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.DependentesTitularTOBuilder;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.to.DependentesTitularTO;

@Component
public class GetDependentesTitularCmd {
	
	@Autowired private DependentesTitularRepository repository;
	@Autowired private DependentesTitularTOBuilder toBuilder;
	
	
	public List<DependentesTitularTO> getAll() {
		List<DependentesTitular> dependentes= repository.findAll();
		if(dependentes == null || dependentes.isEmpty()) {
			return new ArrayList<DependentesTitularTO>();
		}
		return toBuilder.buildAll(dependentes);

	}
	
	public DependentesTitularTO getById(Long id) {
		Optional<DependentesTitular> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Dependente de titular não encontrado");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
	
	public List<DependentesTitularTO> getAllByIdTitular(Long idTitular) {
		Optional<List<DependentesTitular>> dependentes = repository.findAllByTitular(idTitular);
		if(dependentes.isPresent()) {
			return toBuilder.buildAll(dependentes.get());
		}
		return null;
	}
			
	
	public boolean isJaDependente(String cpf) {
		Optional<List<DependentesTitular>> dependentes = repository.findAllDependentesAtivosByCPF(NumeroUtil.somenteNumeros(cpf));
		return dependentes.isPresent();
	}
}
