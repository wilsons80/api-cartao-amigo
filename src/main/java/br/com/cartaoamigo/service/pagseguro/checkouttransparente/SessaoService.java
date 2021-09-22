package br.com.cartaoamigo.service.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.checkouttransparente.GetSessaoCmd;
import br.com.cartaoamigo.ws.pagseguro.to.SessaoTO;

@RestController
@RequestMapping(value = "pagseguro/checkouttransparente/sessao")
public class SessaoService {
	
	@Autowired private GetSessaoCmd getSessaoCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public SessaoTO getSessao() {
		return getSessaoCmd.getSessao();
	}

}
