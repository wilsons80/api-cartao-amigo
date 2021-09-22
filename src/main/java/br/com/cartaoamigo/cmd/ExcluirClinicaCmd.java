package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.ClinicaRepository;
import br.com.cartaoamigo.dao.repository.ClinicasTipoEspecialidadesRepository;
import br.com.cartaoamigo.dao.repository.MedicosClinicasRepository;
import br.com.cartaoamigo.dao.repository.MedicosEspecialidadesClinicasRepository;
import br.com.cartaoamigo.dao.repository.ProcedimentoAssociadoClinicaRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.entity.MedicosClinicas;
import br.com.cartaoamigo.entity.MedicosEspecialiadadesClinicas;
import br.com.cartaoamigo.entity.ProcedimentoAssociadoClinica;
import br.com.cartaoamigo.exception.ClinicaNaoEncontradaException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.exception.ProcedimentoAssociadoClinicaJaExisteException;

@Component
public class ExcluirClinicaCmd {

	@Autowired private ClinicaRepository repository;
	@Autowired private ClinicasTipoEspecialidadesRepository clinicasTipoEspecialidadesRepository;
	@Autowired private MedicosClinicasRepository medicosClinicasRepository;
	@Autowired private MedicosEspecialidadesClinicasRepository medicosEspecialidadesClinicasRepository;
	@Autowired private ProcedimentoAssociadoClinicaRepository procedimentoAssociadoClinicaRepository;
	
	
	public void excluir(Long idClinica) {
		if (Objects.isNull(idClinica)) {
			throw new ParametroNaoInformadoException("Identificador da clínica não informado.");
		}

		Optional<List<ProcedimentoAssociadoClinica>> procedimentoAssociadoClinica = procedimentoAssociadoClinicaRepository.findAllByClinica(idClinica);
		if(procedimentoAssociadoClinica.isPresent()) {
			throw new ProcedimentoAssociadoClinicaJaExisteException("Não é possível excluir, pois já existem procedimentos de associados realizados nessa clínica.");
		}
		
		Optional<List<ClinicasTipoEspecialidades>> clinicasTipoEspecialidades = clinicasTipoEspecialidadesRepository.findAllByClinica(idClinica);
		if(clinicasTipoEspecialidades.isPresent()) {
			clinicasTipoEspecialidadesRepository.deleteAll(clinicasTipoEspecialidades.get());
		}
		
		Optional<List<MedicosEspecialiadadesClinicas>> medicosEspecialidadesClinicas = medicosEspecialidadesClinicasRepository.findAllByClinica(idClinica);
		if(medicosEspecialidadesClinicas.isPresent()) {
			medicosEspecialidadesClinicasRepository.deleteAll(medicosEspecialidadesClinicas.get());
		}
		
		Optional<List<MedicosClinicas>> medicosClinica = medicosClinicasRepository.findAllByClinica(idClinica);
		if(medicosClinica.isPresent()) {
			medicosClinicasRepository.deleteAll(medicosClinica.get());
		}
		
		Clinica entity = repository.findById(idClinica).orElseThrow(() -> new ClinicaNaoEncontradaException("Clínica informada não existe: " + idClinica));
		repository.delete(entity);
	}

}
