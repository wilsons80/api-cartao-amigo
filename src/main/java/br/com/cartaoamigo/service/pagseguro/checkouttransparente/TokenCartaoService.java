package br.com.cartaoamigo.service.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.checkouttransparente.GetTokenCartaoCmd;
import br.com.cartaoamigo.to.ParansTokenCartaoTO;
import br.com.cartaoamigo.ws.pagseguro.to.TokenCartaoTO;

@RestController
@RequestMapping(value = "pagseguro/checkouttransparente/tokencartao")
public class TokenCartaoService {
	
	@Autowired private GetTokenCartaoCmd getCmd;
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenCartaoTO getTokenCartao(@RequestBody ParansTokenCartaoTO tokenCartaoTO) {
		return getCmd.getTokenCartao(tokenCartaoTO);
	}

}
