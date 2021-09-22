package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.condicoesparcelamento;

import br.com.cartaoamigo.ws.pagseguro.to.CondicoesParcelamentoBandeirasTO;

public interface CondicoesParcelamentoService {

	CondicoesParcelamentoBandeirasTO getCondicoesParcelamento(String idSessao, Double valor, String bandeiraCartao, Long maxParcelasSemJuros) throws Exception;
	
}
