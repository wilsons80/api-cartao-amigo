package br.com.cartaoamigo.service.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.split.GetMetodosPagamentoSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.MetodosPagamentoTO;

@RestController
@RequestMapping(value = "pagseguro/split/meiospagamento")
public class MeiosPagamentoSplitService {
	
	@Autowired private GetMetodosPagamentoSplitCmd getCmd;
	
	@GetMapping(path = "/sessao/{idSessao}/valor/{valor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MetodosPagamentoTO get(@PathVariable(name = "idSessao") String idSessao,
			                           @PathVariable(name = "valor") Double valor) {
		return getCmd.get(idSessao, valor);
	}

}
