package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicosClinicasTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicosClinicasRepository;
import br.com.cartaoamigo.entity.MedicosClinicas;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.ClinicaTO;
import br.com.cartaoamigo.to.MedicosClinicasTO;

@Component
public class CadastrarMedicosClinicasCmd {

	@Autowired private MedicosClinicasTOBuilder toBuilder;
	@Autowired private MedicosClinicasRepository repository;
	
	private MedicosClinicas alterar(MedicosClinicasTO to) {
		MedicosClinicas entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Médico não se encontra cadastrado nessa clínica."));
		entity = toBuilder.build(to);
		return repository.save(entity);
	}
	
	public MedicosClinicas cadastrar(MedicosClinicasTO to) {
		MedicosClinicas entity = toBuilder.build(to);	
		entity.setAtivo(true);		
		return repository.save(entity);	
	}
	
	public List<MedicosClinicasTO> alterarLista(List<MedicosClinicasTO> listaTO, Long idClinica) {
		alterarAll(listaTO, idClinica);
		
		Optional<List<MedicosClinicas>> entitys = repository.findAllByClinica(idClinica);
		if(entitys.isPresent()) 
			return toBuilder.buildAll(entitys.get());		
		
		return null;
	}

	private void alterarAll(List<MedicosClinicasTO> listaTO, Long idClinica) {
		
		//Lista do banco de dados
		List<MedicosClinicas> entitys = repository.findAllByClinica(idClinica).orElse(new ArrayList<>());
		
		BiPredicate<MedicosClinicasTO, List<MedicosClinicasTO>> contemNaLista  = (parte, lista) -> lista.stream()
																										 .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
																								                 && 
																								                 registroTO.getId().equals(parte.getId()));
							
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		entitys.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), listaTO)){
											Optional<MedicosClinicas> medicoClinica = repository.findByClinicaAndIdMedico(idClinica, registro.getId());
											if(medicoClinica.isPresent()) {
												medicoClinica.get().setAtivo(false);
												repository.save(medicoClinica.get());
											}
											return true;
										}
										return false;
									  }
		                );
		
		//Adiciona os novos registros
		List<MedicosClinicasTO> novos = listaTO.stream()
		                                      .filter(registro -> Objects.isNull(registro.getId()))
		                                      .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(registro -> {
				registro.setClinica(new ClinicaTO(idClinica));
				cadastrar(registro);
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
