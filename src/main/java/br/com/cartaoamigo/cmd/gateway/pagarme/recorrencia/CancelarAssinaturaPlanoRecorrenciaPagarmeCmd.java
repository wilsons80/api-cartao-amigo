package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.CancelarAssinaturaCmd;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.CancelarAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;


@Component
public class CancelarAssinaturaPlanoRecorrenciaPagarmeCmd {

	@Autowired private AssinaturaPlanoRecorrenciaService service;
	@Autowired private CancelarAssinaturaCmd cancelarAssinaturaCmd;
	
	public RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(String codigoAssinaturaPagarme) {
		try {
			cancelarAssinaturaCmd.cancelarAssinatura(codigoAssinaturaPagarme, true);
			
			CancelarAssinaturaPlanoTO cancelarTO = new CancelarAssinaturaPlanoTO();
			cancelarTO.setCancel_pending_invoices(true);
			
			return service.cancelarAssinatura(codigoAssinaturaPagarme, cancelarTO);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao cancelar a assinatura: " + e.getMessage());
		}
	}

}
