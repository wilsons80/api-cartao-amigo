package br.com.cartaoamigo.service.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.checkouttransparente.GetBandeirasCmd;
import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;


@RestController
@RequestMapping(value = "pagseguro/checkouttransparente/bandeira")
public class BandeiraService {
	
	@Autowired private GetBandeirasCmd getBandeirasCmd;
	
	@GetMapping(path = "/sessao/{idSessao}/bin/{binCartao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BandeiraCartaoTO getSessao(@PathVariable(name = "idSessao") String idSessao,
			                          @PathVariable(name = "binCartao") String binCartao) {
		return getBandeirasCmd.getBandeira(idSessao, binCartao);
	}

}
