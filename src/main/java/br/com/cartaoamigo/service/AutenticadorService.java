package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AutenticadorCmd;
import br.com.cartaoamigo.to.LoginTO;
import br.com.cartaoamigo.to.TrocaSenhaTO;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@RestController
@RequestMapping(value = "autenticador")
public class AutenticadorService {

	@Autowired
	private AutenticadorCmd autenticadorLoginCmd;

	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioLogadoTO login(@RequestBody LoginTO user) {
		return autenticadorLoginCmd.autenticar(user);
	}
	
	@PostMapping(path = "/trocar-senha", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void trocarSenha(@RequestBody TrocaSenhaTO trocaSenhaTO) {
		autenticadorLoginCmd.trocarSenha(trocaSenhaTO);
	}
	
}
