package br.com.cartaoamigo.service.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.checkouttransparente.PagamentoCartaoCreditoCmd;
import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoPagamentoCheckoutTransparenteCartaoCreditoTO;

@RestController
@RequestMapping(value = "pagseguro/checkouttransparente/cr/pagamento")
public class PagamentoCheckoutTransparenteCartaoCreditoService {
	
	@Autowired private PagamentoCartaoCreditoCmd pagamentoCmd;
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoPagamentoCheckoutTransparenteCartaoCreditoTO realizarPagamento(@RequestBody PagamentoCheckoutTransparenteCartaoCreditoTO dadosTO) {
		return pagamentoCmd.realizarCheckoutTransparente(dadosTO);
	}
	
}
