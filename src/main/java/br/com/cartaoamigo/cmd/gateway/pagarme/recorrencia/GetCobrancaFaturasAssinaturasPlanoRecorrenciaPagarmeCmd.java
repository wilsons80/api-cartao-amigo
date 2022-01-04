package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.cobranca.CobrancaFaturaAssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.CobrancaFaturaTO;


@Component
public class GetCobrancaFaturasAssinaturasPlanoRecorrenciaPagarmeCmd {

	@Autowired private CobrancaFaturaAssinaturaPlanoRecorrenciaService service;
	
	public CobrancaFaturaTO getCobrancaFaturasDaAssinatura(String idCobranca) {
		try {
			return service.getCobrancaFaturasDaAssinatura(idCobranca);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter a cobrança da fatura: " + e.getMessage());
		}
	}
	

}
