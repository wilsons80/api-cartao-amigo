package br.com.cartaoamigo.rule;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.MedicosClinicasRepository;
import br.com.cartaoamigo.entity.MedicosClinicas;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;

@Component
public class VerificarMedicosClinicasRule {
	
	@Autowired private MedicosClinicasRepository repository;
	

	public void verificar(Long idMedico, Long idClinica) {
		if(Objects.nonNull(idMedico)) {
			Optional<MedicosClinicas> medico = repository.findByClinicaAndIdMedico(idClinica, idMedico);
			if(medico.isPresent()) {
				throw new PessoaFisicaJaExisteException("Esse médico já está cadastrado nessa clínica. ");
			}
		}
	}

}
