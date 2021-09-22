package br.com.cartaoamigo.service.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.split.GetSessaoSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.SessaoTO;

@RestController
@RequestMapping(value = "pagseguro/split/sessao")
public class SessaoSplitService {
	
	@Autowired private GetSessaoSplitCmd getSessaoCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public SessaoTO getSessao() {
		return getSessaoCmd.getSessao();
	}

}
