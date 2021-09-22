package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.dao.GetProcedimentoAssociadoClinicaDao;
import br.com.cartaoamigo.dao.dto.ProcedimentoAssociadoClinicaDTO;

@RestController
@RequestMapping(value = "procedimentos")
public class ProcedimentoAssociadoClinicaService {

	@Autowired private GetProcedimentoAssociadoClinicaDao getCmd;
	
	@GetMapping(path = "/titular/{idTitular}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProcedimentoAssociadoClinicaDTO> getAllTitular(@PathVariable(name = "idTitular") Long idTitular) {
		return getCmd.getAllConsultasRealizadas(idTitular);
	}

}
