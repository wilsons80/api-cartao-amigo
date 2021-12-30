package br.com.cartaoamigo.service.gateway.pagseguro.split;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.pagseguro.split.PagamentoBoletoSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.CheckoutTransparenteBoletoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoTO;

@RestController
@RequestMapping(value = "pagseguro/split/boleto/pagamento")
public class PagamentoSplitBoletoService {
	
	@Autowired private PagamentoBoletoSplitCmd pagamentoCmd;
	
	@Transactional
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoSplitPagamentoTO realizarPagamento(@RequestBody CheckoutTransparenteBoletoTO dadosTO) {
		return pagamentoCmd.realizarCheckoutTransparente(dadosTO);
	}
	
}
