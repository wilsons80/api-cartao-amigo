package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.CadastrarMedicosClinicasCmd;
import br.com.cartaoamigo.to.MedicosClinicasTO;

@RestController
@RequestMapping(value = "medicosclinica")
public class MedicosClinicaService {
	
	@Autowired private CadastrarMedicosClinicasCmd cadastrarCmd;
	
	@PostMapping(path = "/clinica/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MedicosClinicasTO> cadastrar(@PathVariable (name = "idClinica") Long idClinica,
											 @RequestBody List<MedicosClinicasTO> medicosClinicasTO){
		return cadastrarCmd.alterarLista(medicosClinicasTO, idClinica);
	}

}
