package br.com.cartaoamigo.service.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetTokenCartaoRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.to.ParansTokenCartaoPagarmeTO;
import br.com.cartaoamigo.ws.pagarme.to.TokenCartaoTO;


@RestController
@RequestMapping(value = "pagarme/recorrencia/tokencartao")
public class TokenCartaoRecorrenciaPagarmeService {
	
	@Autowired private GetTokenCartaoRecorrenciaPagarmeCmd getCmd;
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenCartaoTO getTokenCartao(@RequestBody ParansTokenCartaoPagarmeTO tokenCartaoTO) {
		return getCmd.getTokenCartao(tokenCartaoTO);
	}

}
