package br.com.cartaoamigo.ws.pagseguro.split.meiopagamento;

import br.com.cartaoamigo.ws.pagseguro.to.MetodosPagamentoTO;

public interface MetodosPagamentoSplitService {
	
	MetodosPagamentoTO getMetodosPagamento(String idSessao, Double valor) throws Exception;

}
