package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PerfilAcessoTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAcessoRepository;
import br.com.cartaoamigo.entity.PerfilAcesso;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.PerfilAcessoTO;

@Component
public class GetPerfilAcessoCmd {

	@Autowired private PerfilAcessoRepository repository;
	@Autowired private PerfilAcessoTOBuilder toBuilder;
	
	public List<PerfilAcessoTO> getAll() {
		List<PerfilAcesso> perfis = repository.findAll();
		if(perfis == null || perfis.isEmpty()) {
			return new ArrayList<PerfilAcessoTO>();
		}
		return toBuilder.buildAll(perfis);
 	}
	
	public PerfilAcessoTO getById(Long id) {
		Optional<PerfilAcesso> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Perfil de acesso não encontrado");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
