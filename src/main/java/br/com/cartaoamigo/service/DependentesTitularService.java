package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetDependentesTitularCmd;
import br.com.cartaoamigo.to.DependentesTitularTO;

@RestController
@RequestMapping(value = "dependentestitular")
public class DependentesTitularService {

	@Autowired GetDependentesTitularCmd getCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DependentesTitularTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DependentesTitularTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}
	
	@GetMapping(path = "/titular/{idTitular}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DependentesTitularTO> getAllByIdTitular(@PathVariable(name = "idTitular") Long idTitular) {
		return getCmd.getAllByIdTitular(idTitular);
	}
	
	@GetMapping(path = "/isdependente/{cpf}")
	public boolean isJaDependente(@PathVariable(name = "cpf") String cpf) {
		return getCmd.isJaDependente(cpf);
	}
	

}
