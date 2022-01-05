package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.fatura.FaturaAssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.ListaFaturasAssinaturaPlanoTO;


@Component
public class GetFaturasAssinaturasPlanoRecorrenciaPagarmeCmd {

	@Autowired private FaturaAssinaturaPlanoRecorrenciaService service;
	
	public ListaFaturasAssinaturaPlanoTO getFaturasDaAssinatura(String idCliente, String idAssinatura) {
		try {
			return service.getFaturasDaAssinatura(idCliente, idAssinatura);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter as faturas da assinatura: " + e.getMessage());
		}
	}
	

}