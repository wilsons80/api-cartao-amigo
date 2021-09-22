package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.ExcluirRedefinirSenhaCmd;
import br.com.cartaoamigo.cmd.GetRedefinirSenhaCmd;
import br.com.cartaoamigo.cmd.GravarEnvioEmailCmd;
import br.com.cartaoamigo.cmd.RedefinirNovaSenhaCmd;
import br.com.cartaoamigo.to.RedefinirNovaSenhaTO;
import br.com.cartaoamigo.to.RedefinirSenhaTO;
import br.com.cartaoamigo.to.SolicitarRedefinirSenhaTO;


@RestController
@RequestMapping(value = "redefinirsenha")
public class RedefinirSenhaService {

	@Autowired private GetRedefinirSenhaCmd getCmd;
	@Autowired private ExcluirRedefinirSenhaCmd excluirCmd;
	@Autowired private RedefinirNovaSenhaCmd redefinirNovaSenhaCmd;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	
	@GetMapping(path = "/codigovalidacao/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE )
	public RedefinirSenhaTO findByCodigoValidacao(@PathVariable(name = "codigo") String codigo) {
		return getCmd.findByCodigoValidacao(codigo);
	}
	
	@PostMapping(path = "/trocar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void trocarSenha(@RequestBody RedefinirNovaSenhaTO to) {
		redefinirNovaSenhaCmd.trocarSenha(to);
	}
	
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletarRedefinirSenha(@PathVariable(name = "id") Long id) {
		excluirCmd.excluirRedefinirSenha(id);
	}
	
	
	@PostMapping(path = "/email")
	public void gravarEnvioEmailRedefinirSenha(@RequestBody SolicitarRedefinirSenhaTO emailTO) {
		gravarEnvioEmailCmd.gravarEnvioEmailRedefinirSenha(emailTO);
	}
	
}
