package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetPessoaFisicaCmd;
import br.com.cartaoamigo.to.ComboAssociadoTO;
import br.com.cartaoamigo.to.PessoaFisicaTO;

@RestController
@RequestMapping(value = "pessoafisica")
public class PessoaFisicaService {
	
	@Autowired
	private GetPessoaFisicaCmd getCmd;
	
	
	@GetMapping(path = "/associados/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ComboAssociadoTO> getAllByCombo() {
		return getCmd.getAllAssociadosByCombo();
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PessoaFisicaTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PessoaFisicaTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

}
