package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.GetAssociadosDao;
import br.com.cartaoamigo.dao.dto.AssociadoComboDTO;
import br.com.cartaoamigo.dao.dto.AssociadosDTO;

@Component
public class GetAssociadosCmd {

	@Autowired private GetAssociadosDao getAssociadosDao;
	
	public List<AssociadosDTO> getAllFilter(Long idPessoaFisicaTitular, String ativo, LocalDate dataInicioGerado, LocalDate dataFimGerado) {
		List<AssociadosDTO> associados = getAssociadosDao.getAllFilterAssociados(idPessoaFisicaTitular, ativo, dataInicioGerado, dataFimGerado);
		if(associados.isEmpty()) {return null;}
		return associados;
	}
	
	
	public List<AssociadoComboDTO> getAllCombo() {
		List<AssociadoComboDTO> associados = getAssociadosDao.getAllCombo();
		if(associados.isEmpty()) {return null;}
		return associados;
	}

}
