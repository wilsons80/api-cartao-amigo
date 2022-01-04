package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;


@Component
public class CancelarAssinaturaPlanoRecorrenciaPagarmeCmd {

	@Autowired private AssinaturaPlanoRecorrenciaService service;
	
	public RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(String idAssinatura) {
		try {
			return service.cancelarAssinatura(idAssinatura);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao cancelar a assinatura: " + e.getMessage());
		}
	}

}
