package br.com.cartaoamigo.service.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.split.GetCondicoesParcelamentoSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.CondicaoParcelamentoTO;

@RestController
@RequestMapping(value = "pagseguro/split/condicoesparcelas")
public class CondicoesParcelamentoSplitService {
	
	@Autowired private GetCondicoesParcelamentoSplitCmd getCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public CondicaoParcelamentoTO get(@RequestParam(value = "sessionId", required = true) String idSessao,
			                          @RequestParam(value = "amount", required = true) Double valor,
			                          @RequestParam(value = "creditCardBrand", required = true) String bandeiraCartao,
			                          @RequestParam(value = "maxInstallmentNoInterest", required = true) Long maxParcelasSemJuros) {
		return getCmd.getCondicoesParcelamento(idSessao, valor, bandeiraCartao, maxParcelasSemJuros);
	}

}
