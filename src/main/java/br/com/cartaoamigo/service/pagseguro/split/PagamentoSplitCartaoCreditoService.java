package br.com.cartaoamigo.service.pagseguro.split;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.split.PagamentoCartaoCreditoSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.CheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoTO;

@RestController
@RequestMapping(value = "pagseguro/split/cr/pagamento")
public class PagamentoSplitCartaoCreditoService {
	
	@Autowired private PagamentoCartaoCreditoSplitCmd pagamentoCmd;
	
	@Transactional
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoSplitPagamentoTO realizarPagamento(@RequestBody CheckoutTransparenteCartaoCreditoTO dadosTO) {
		return pagamentoCmd.realizarCheckoutTransparente(dadosTO);
	}
	
}
