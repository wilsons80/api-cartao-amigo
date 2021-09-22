package br.com.cartaoamigo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.CadastrarTitularCmd;
import br.com.cartaoamigo.to.TitularTO;

 @RestController
 @RequestMapping(value = "contaassociado")
 public class ContaAssociadoService {
	
	@Autowired private CadastrarTitularCmd salvarCmd;
	
	@Transactional
	@PostMapping(path = "/criar", produces = MediaType.APPLICATION_JSON_VALUE)
	public TitularTO salvar(@RequestBody TitularTO to) {
		return salvarCmd.cadastrar(to);
	}
	
}
