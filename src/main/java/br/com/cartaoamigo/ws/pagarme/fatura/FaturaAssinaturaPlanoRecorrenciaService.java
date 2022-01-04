package br.com.cartaoamigo.ws.pagarme.fatura;

import java.util.List;

import br.com.cartaoamigo.ws.pagarme.to.FaturaAssinaturaPlanoTO;

public interface FaturaAssinaturaPlanoRecorrenciaService {
	
	List<FaturaAssinaturaPlanoTO> getFaturasDaAssinatura(String idCliente, String idAssinatura) throws Exception ;
	
}
