package br.com.cartaoamigo.ws.pagarme.fatura;

import br.com.cartaoamigo.ws.pagarme.to.ListaFaturasAssinaturaPlanoTO;

public interface FaturaAssinaturaPlanoRecorrenciaService {
	
	ListaFaturasAssinaturaPlanoTO getFaturasDaAssinatura(String idCliente, String idAssinatura) throws Exception ;
	
}
