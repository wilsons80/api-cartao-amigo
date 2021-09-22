package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetPerfilAmbienteSistemaCmd;
import br.com.cartaoamigo.cmd.SalvarPerfilAmbienteSistemaCmd;
import br.com.cartaoamigo.to.PerfilAmbienteSistemaTO;


@RestController
@RequestMapping(value = "ambienteusuario")
public class PerfilAmbienteSistemaService {

	@Autowired private SalvarPerfilAmbienteSistemaCmd salvarCmd;
	@Autowired private GetPerfilAmbienteSistemaCmd getCmd;
	
	
	@GetMapping(path = "/perfil", produces = MediaType.APPLICATION_JSON_VALUE )
	public PerfilAmbienteSistemaTO get() {
		return getCmd.get();
	}
	
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PerfilAmbienteSistemaTO salvar(@RequestBody PerfilAmbienteSistemaTO to) {
		return salvarCmd.salvar(to);
	}
	
}
