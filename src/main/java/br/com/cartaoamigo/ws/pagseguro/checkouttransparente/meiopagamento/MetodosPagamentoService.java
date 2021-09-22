package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.meiopagamento;

import br.com.cartaoamigo.ws.pagseguro.to.MetodosPagamentoTO;

public interface MetodosPagamentoService {
	
	MetodosPagamentoTO getMetodosPagamento(String idSessao, Double valor) throws Exception;

}
