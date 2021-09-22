package br.com.cartaoamigo.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.mail.EnviarEmailCartaoAmigoCmd;

@RestController
@RequestMapping(value = "email")
public class EnvioEmailService {
	
	@Autowired private EnviarEmailCartaoAmigoCmd enviarEmailCmd;
	
	
	@PostMapping(path = "/{idPessoa}")
	public void salvarNotificacao(@PathVariable(name = "idPessoa") Long idPessoa) {
		enviarEmailCmd.enviarEmail(idPessoa);
	}
	

}
