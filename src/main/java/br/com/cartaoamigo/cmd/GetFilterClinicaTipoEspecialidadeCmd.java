package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.GetClinicaTipoEspecialidadeDao;
import br.com.cartaoamigo.dao.dto.ClinicaTipoEspecialidadeDTO;

@Component
public class GetFilterClinicaTipoEspecialidadeCmd {
	
	
	@Autowired private GetClinicaTipoEspecialidadeDao getClinicaTipoEspecialidadeDao;
	
	public List<ClinicaTipoEspecialidadeDTO> getAllFilterClinicaTipoEspecialidade(String cidade, String uf, Long idTipoEspecialidade) {
		List<ClinicaTipoEspecialidadeDTO> clinicaTipoEspecialidade = getClinicaTipoEspecialidadeDao.getAllFilterClinicaTipoEspecialidade(cidade, uf, idTipoEspecialidade);
		if(clinicaTipoEspecialidade.isEmpty()) {return null;}
		return clinicaTipoEspecialidade;
	}

}
