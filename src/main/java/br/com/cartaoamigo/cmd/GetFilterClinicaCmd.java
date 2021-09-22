package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.GetClinicaDao;
import br.com.cartaoamigo.dao.dto.ClinicaDTO;

@Component
public class GetFilterClinicaCmd {
	
	
	@Autowired private GetClinicaDao getClinicaDao;
	
	public List<ClinicaDTO> getAllFilterClinica(Long idClinica, String ativo, LocalDate dataInicioGerado, LocalDate dataFimGerado, String bairro, Long idTipoEspecialidade) {
		List<ClinicaDTO> clinicas = getClinicaDao.getAllFilterClinica(idClinica, ativo, dataInicioGerado, dataFimGerado, bairro, idTipoEspecialidade);
		if(clinicas.isEmpty()) {return null;}
		return clinicas;
	}

}
