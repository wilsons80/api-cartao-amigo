package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AutenticadorCmd;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@RestController
@RequestMapping(value = "token")
public class TokenService {
	
	@Autowired
	private AutenticadorCmd autenticadorLoginCmd;
	
	@RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
	public UsuarioLogadoTO getUsuarioLogado() {
		return autenticadorLoginCmd.refreshToken();
	}

}
