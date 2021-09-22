package br.com.cartaoamigo.ws.pagseguro.split.pagamento;

import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteBoletoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoBoletoTO;

public interface PagamentoSplitBoletoService {
	
	RetornoSplitPagamentoBoletoTO realizarPagamentoCheckoutTransparente(PagamentoCheckoutTransparenteBoletoTO param) throws Exception;

}
