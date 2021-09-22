package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.CorretorTOBuilder;
import br.com.cartaoamigo.dao.GetCorretorDao;
import br.com.cartaoamigo.dao.dto.CorretorComboDTO;
import br.com.cartaoamigo.dao.dto.CorretorDTO;
import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.to.CorretorTO;

@Component
public class GetCorretorCmd {

	@Autowired private CorretorRepository repository;
	@Autowired private CorretorTOBuilder toBuilder;
	@Autowired private GetCorretorDao   getDao;
	
	public List<CorretorComboDTO> getAllCombo() {
		List<CorretorComboDTO> associados = getDao.getAllCombo();
		if(associados.isEmpty()) {return null;}
		return associados;
	}
	
	public CorretorTO getByToken(String token) {
		Optional<Corretor> entity = repository.findByToken(token);
		if(!entity.isPresent()) {return null;}
		return toBuilder.buildTO(entity.get());
	}
	
	public CorretorTO getById(Long idClinica) {
		Optional<Corretor> entity = repository.findById(idClinica);
		if(!entity.isPresent()) {return null;}
		return toBuilder.buildTO(entity.get());
	}
	
	public List<CorretorDTO> getAllFilter(Long idCorretor, String ativo, LocalDate dataInicioCadastro, LocalDate dataFimCadastro) {
		List<CorretorDTO> dtos = getDao.getAllFilter(idCorretor, ativo, dataInicioCadastro, dataFimCadastro);
		if(dtos.isEmpty()) {return null;}
		return dtos;
	}

}
