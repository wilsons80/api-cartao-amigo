package br.com.cartaoamigo.service.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.split.GetTokenCartaoSplitCmd;
import br.com.cartaoamigo.to.ParansTokenCartaoTO;
import br.com.cartaoamigo.ws.pagseguro.to.TokenCartaoTO;

@RestController
@RequestMapping(value = "pagseguro/split/tokencartao")
public class TokenCartaoSplitService {
	
	@Autowired private GetTokenCartaoSplitCmd getCmd;
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenCartaoTO getTokenCartao(@RequestBody ParansTokenCartaoTO tokenCartaoTO) {
		return getCmd.getTokenCartao(tokenCartaoTO);
	}

}
