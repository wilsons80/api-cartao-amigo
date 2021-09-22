package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicaTOBuilder;
import br.com.cartaoamigo.dao.GetClinicaDao;
import br.com.cartaoamigo.dao.dto.ClinicaComboDTO;
import br.com.cartaoamigo.dao.repository.ClinicaRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.to.ClinicaTO;

@Component
public class GetProcedimentoAssociadosClinicaCmd {

	@Autowired private ClinicaRepository clinicaRepository;
	@Autowired private ClinicaTOBuilder clinicaTOBuilder;
	@Autowired private GetClinicaDao getClinicaDao;
	
	public List<ClinicaComboDTO> getAllCombo() {
		List<ClinicaComboDTO> associados = getClinicaDao.getAllCombo();
		if(associados.isEmpty()) {return null;}
		return associados;
	}
	
	public ClinicaTO getById(Long idClinica) {
		Optional<Clinica> entity = clinicaRepository.findById(idClinica);
		if(!entity.isPresent()) {return null;}
		return clinicaTOBuilder.buildTO(entity.get());
	}
	
	public List<ClinicaTO> getAll() {
		Optional<List<Clinica>> entitys = Optional.ofNullable(clinicaRepository.findAll());
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}
	
	public List<ClinicaTO> findAllAtivos() {
		Optional<List<Clinica>> entitys = clinicaRepository.findAllByStatus(true);
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}

	public List<ClinicaTO> findAllInativos() {
		Optional<List<Clinica>> entitys = clinicaRepository.findAllByStatus(false);
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}
	
	
	public List<ClinicaTO> findAllByBairro(String bairro){
		Optional<List<Clinica>> entitys = clinicaRepository.findAllByBairro(bairro);
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}
	
	public List<ClinicaTO> findAllByTipoEspecialidade(Long idTipoEspecialidade){
		Optional<List<Clinica>> entitys = clinicaRepository.findAllByTipoEspecialidade(idTipoEspecialidade);
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}
	
	public List<ClinicaTO> findAllByValorAssociado(Double valor){
		Optional<List<Clinica>> entitys = clinicaRepository.findAllByValorAssociado(valor);
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}
	
	public List<ClinicaTO> findAllByValorParticular(Double valor){
		Optional<List<Clinica>> entitys = clinicaRepository.findAllByValorParticular(valor);
		if(!entitys.isPresent()) {return null;}
		return clinicaTOBuilder.buildAll(entitys.get());
	}
}
