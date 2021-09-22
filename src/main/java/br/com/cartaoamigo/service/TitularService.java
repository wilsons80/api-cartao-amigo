package br.com.cartaoamigo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AlterarTitularCmd;
import br.com.cartaoamigo.cmd.CadastrarTitularCmd;
import br.com.cartaoamigo.cmd.ExcluirTitularCmd;
import br.com.cartaoamigo.cmd.GetTitularCmd;
import br.com.cartaoamigo.to.TitularTO;

 @RestController
 @RequestMapping(value = "titular")
 public class TitularService {
	
	@Autowired private GetTitularCmd getCmd;
	@Autowired private CadastrarTitularCmd salvarCmd;
	@Autowired private AlterarTitularCmd alterarCmd;
	@Autowired private ExcluirTitularCmd excluirCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TitularTO> getAll() {
		return getCmd.getAll();	
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TitularTO getById(@PathVariable(name = "id") 	Long idTitular) {
		return getCmd.getById(idTitular);
	}
	
	@GetMapping(path = "/usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TitularTO getByIdPessoaFisica(@PathVariable(name = "idUsuario") Long idUsuario) {
		return getCmd.getByIdUsuario(idUsuario);
	}
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public TitularTO salvar(@RequestBody TitularTO to) {
		return salvarCmd.cadastrar(to);
	}
	
	@PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public TitularTO alterar(@RequestBody TitularTO to) {
		return alterarCmd.alterar(to);
	}

	@Transactional
	@DeleteMapping(path = "/sempagamentos/titular/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void excluirDadosSemPagamento(@PathVariable(name = "id") Long idTitular) {
		excluirCmd.excluirDadosSemPagamento(idTitular);
	}

}
