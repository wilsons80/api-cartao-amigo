package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.DependentesTitularTOBuilder;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.to.DependentesTitularTO;

@Component
public class AlterarDependentesTitularCmd  extends AbstractAlterarListaCmd<DependentesTitular, DependentesTitularTO, Titular>{
	
	@Autowired DependentesTitularTOBuilder toBuilder;
	@Autowired DependentesTitularRepository repository;
	@Autowired CadastrarDependentesTitularCmd cadastrarDependentesTitularCmd;
	
	@Override
	protected DependentesTitularTO getTO(DependentesTitular entity) {
		return toBuilder.buildTO(entity);
	}
	
	@Override
	protected List<DependentesTitularTO> getTOListaBanco(List<DependentesTitular> lista) {
		return toBuilder.buildAll(lista);
	}
	
	@Override
	protected List<DependentesTitular> getListaBanco(Titular pai) {
		return repository.findAll();
	}
	
	@Override
	protected Long getIdentificadorTO(DependentesTitularTO to) {
		return to.getId();
	}	
	
	@Override
	protected void cadastrar(DependentesTitularTO to, Titular p) {
		cadastrarDependentesTitularCmd.cadastrar(to, p);
		
	}	
	
	@Override
	protected void deletar(DependentesTitular registro) {
		registro.setAtivo(false);
		repository.save(registro);
	}
	
}