package br.com.cartaoamigo.service.gateway.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.pagseguro.split.GetBandeirasSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;


@RestController
@RequestMapping(value = "pagseguro/split/bandeira")
public class BandeiraSplitService {
	
	@Autowired private GetBandeirasSplitCmd getBandeirasCmd;
	
	@GetMapping(path = "/sessao/{idSessao}/bin/{binCartao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BandeiraCartaoTO getSessao(@PathVariable(name = "idSessao") String idSessao,
			                          @PathVariable(name = "binCartao") String binCartao) {
		return getBandeirasCmd.getBandeira(idSessao, binCartao);
	}

}
