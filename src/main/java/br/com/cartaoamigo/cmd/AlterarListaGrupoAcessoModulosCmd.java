package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoModulosTOBuilder;
import br.com.cartaoamigo.dao.repository.GrupoAcessoModulosRepository;
import br.com.cartaoamigo.entity.GrupoAcesso;
import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.GrupoAcessoModulosTO;

@Component
public class AlterarListaGrupoAcessoModulosCmd {

	@Autowired private GrupoAcessoModulosRepository repository;
	@Autowired private GrupoAcessoModulosTOBuilder toBuilder;
	@Autowired private ExcluirGrupoAcessoModuloCmd excluirCmd;
	@Autowired private CadastrarGrupoAcessoModulosCmd cadastrarCmd;
	
	private GrupoAcessoModulos alterar(GrupoAcessoModulosTO to, GrupoAcesso grupoAcesso) {
		GrupoAcessoModulos entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Grupo de acesso do modulo informado não existe: " + to.getId()));
		entity = toBuilder.build(to);
		entity = repository.save(entity);
		
		cadastrarCmd.cadastrarModuloPai(to, grupoAcesso);
		
		return entity;
	}
	

	public void alterarAll(List<GrupoAcessoModulosTO> listaTO, GrupoAcesso grupoAcesso) {
		
		//Lista do banco de dados
		List<GrupoAcessoModulos> entitys = repository.findAllFilhosByGrupoAcesso(grupoAcesso.getId()).orElse(new ArrayList<>());
		
		BiPredicate<GrupoAcessoModulosTO, List<GrupoAcessoModulosTO>> contemNaLista  = (parte, lista) -> lista.stream()
																											  .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                 && 
																									                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		entitys.removeIf(registro -> {
										if(!registro.getModulo().getAgrupador() && !contemNaLista.test(toBuilder.buildTO(registro), listaTO)){
											excluirCmd.excluir(registro.getId());
											return true;
										}
										return false;
									  }
		                );
		
		//Adiciona os novos registros
		List<GrupoAcessoModulosTO> novos = listaTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(registro -> {
				cadastrarCmd.cadastrar(registro, grupoAcesso);
			});
		}

		//Atualiza os registros 
		listaTO.stream()
               .filter(registro -> Objects.nonNull(registro.getId())) 
               .forEach( registro -> {
										if(contemNaLista.test(registro, toBuilder.buildAll(entitys))){
											alterar(registro, grupoAcesso);
										}
		});
	}

}
