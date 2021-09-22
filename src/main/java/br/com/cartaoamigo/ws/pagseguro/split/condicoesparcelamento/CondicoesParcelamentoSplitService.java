package br.com.cartaoamigo.ws.pagseguro.split.condicoesparcelamento;

import br.com.cartaoamigo.ws.pagseguro.to.CondicaoParcelamentoTO;

public interface CondicoesParcelamentoSplitService {

	CondicaoParcelamentoTO getCondicoesParcelamento(String idSessao, Double valor, String bandeiraCartao, Long maxParcelasSemJuros) throws Exception;
	
}
