package br.com.cartaoamigo.ws.pagarme.cobranca;

import br.com.cartaoamigo.ws.pagarme.to.CobrancaFaturaTO;

public interface CobrancaFaturaAssinaturaPlanoRecorrenciaService {
	
	CobrancaFaturaTO getCobrancaFaturasDaAssinatura(String idCobranca) throws Exception ;
	
}
