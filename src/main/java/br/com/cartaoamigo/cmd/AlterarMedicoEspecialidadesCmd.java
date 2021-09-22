package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicoEspecialidadesTOBuilder;
import br.com.cartaoamigo.builder.MedicoTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicoEspecialidadesRepository;
import br.com.cartaoamigo.entity.Medico;
import br.com.cartaoamigo.entity.MedicoEspecialidades;
import br.com.cartaoamigo.rule.CamposObrigatoriosMedicoEspecialidadeRule;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;
import br.com.cartaoamigo.to.MedicoTO;

@Component
public class AlterarMedicoEspecialidadesCmd {
	
	@Autowired private MedicoEspecialidadesRepository repository;
	@Autowired private MedicoEspecialidadesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMedicoEspecialidadeRule rule;
	@Autowired private ExcluirMedicoEspecialidadesCmd excluirCmd;
	@Autowired private SalvarMedicoEspecialidadesCmd cadastrarCmd;
	@Autowired private MedicoTOBuilder medicoTOBuilder;
	@Autowired private GetMedicoCmd buscarMedicoCmd;
	
	private MedicoEspecialidadesTO alterar(MedicoEspecialidadesTO to) {
		rule.verificar(to);
		
		MedicoEspecialidades entity = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(entity));
	}
	
	
	public List<MedicoEspecialidadesTO> alterarAll(List<MedicoEspecialidadesTO> listaTO, Long idMedico) {
		MedicoTO medico = buscarMedicoCmd.buscarPorId(idMedico);
		alterarAll(listaTO, medicoTOBuilder.build(medico));
		
		List<MedicoEspecialidades> entitys = repository.findAllByMedico(medico.getId()).orElse(new ArrayList<>());
		return toBuilder.buildAll(entitys);
	}
	


	public void alterarAll(List<MedicoEspecialidadesTO> listaTO, Medico medico) {
		
		//Lista do banco de dados
		List<MedicoEspecialidades> entitys = repository.findAllByMedico(medico.getId()).orElse(new ArrayList<>());
		
		BiPredicate<MedicoEspecialidadesTO, List<MedicoEspecialidadesTO>> contemNaLista  = (parte, lista) -> lista.stream()
																											  .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																									                 && 
																									                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		entitys.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), listaTO)){
											excluirCmd.excluir(registro.getId());
											return true;
										}
										return false;
									  }
		                );
		
		//Adiciona os novos registros
		List<MedicoEspecialidadesTO> novos = listaTO.stream()
				                                        .filter(registro -> Objects.isNull(registro.getId()))
				                                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(registro -> {
				cadastrarCmd.cadastrar(registro, medico);
			});
		}

		//Atualiza os registros 
		listaTO.stream()
               .filter(registro -> Objects.nonNull(registro.getId())) 
               .forEach( registro -> {
										if(contemNaLista.test(registro, toBuilder.buildAll(entitys))){
											alterar(registro);
										}
		});
	}


}





