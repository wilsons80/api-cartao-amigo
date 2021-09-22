package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.DependentesTitularTOBuilder;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;
import br.com.cartaoamigo.to.DependentesTitularTO;

@Component
public class AlterarListaDependentesCmd {

	@Autowired private DependentesTitularRepository repository;
	@Autowired private DependentesTitularTOBuilder toBuilder;
	@Autowired private CadastrarDependentesTitularCmd cadastrarCmd;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	
	private DependentesTitular alterar(DependentesTitularTO to, Titular titular) {
		DependentesTitular entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Dependente informado não existe: " + to.getId()));
		
		Optional<PessoaFisica> email = pessoaFisicaRepository.findByEmailDeOutraPessoaFisica(to.getPessoaFisica().getEmail().toUpperCase(), to.getPessoaFisica().getId());
		if(email.isPresent()) {
			throw new PessoaFisicaJaExisteException("O e-mail '" + to.getPessoaFisica().getEmail() +"' já está cadastrado para outro associado.");
		}
		
		entity = toBuilder.build(to);
		pessoaFisicaRepository.save(entity.getPessoaFisica());
		entity = repository.save(entity);
		
		return entity;
	}
	

	public void alterarAll(List<DependentesTitularTO> listaTO, Titular titular) {
		
		//Lista do banco de dados
		List<DependentesTitular> entitys = repository.findAllByTitular(titular.getId()).orElse(new ArrayList<>());
		
		BiPredicate<DependentesTitularTO, List<DependentesTitularTO>> contemNaLista  = (parte, lista) -> lista.stream()
																											  .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                 && 
																									                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		entitys.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), listaTO)){
											registro.setAtivo(false);
											repository.save(registro);
											return true;
										}
										return false;
									  }
		                );
		
		//Adiciona os novos registros
		List<DependentesTitularTO> novos = listaTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(registro -> {
				cadastrarCmd.cadastrar(registro, titular);
			});
		}

		//Atualiza os registros 
		listaTO.stream()
               .filter(registro -> Objects.nonNull(registro.getId())) 
               .forEach( registro -> {
										if(contemNaLista.test(registro, toBuilder.buildAll(entitys))){
											alterar(registro, titular);
										}
		});
	}

}
