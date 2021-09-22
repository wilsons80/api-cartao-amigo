package br.com.cartaoamigo.ws.pagseguro.split.pagamento;

import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoCartaoCreditoTO;

public interface PagamentoSplitCartaoCreditoService {
	
	RetornoSplitPagamentoCartaoCreditoTO realizarPagamentoCheckoutTransparente(PagamentoCheckoutTransparenteCartaoCreditoTO param) throws Exception;

}
