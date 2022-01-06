package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.to.WebHookPagarMeTO;
import br.com.cartaoamigo.ws.pagarme.webhook.WebhookPagarMeService;


@Component
public class GetWebhookPagarmeCmd {
	@Autowired private WebhookPagarMeService service;
	
	public WebHookPagarMeTO getWebhook(String idWebhook) {
		try {
			WebHookPagarMeTO webHookPagarMeTO = service.getWebhook(idWebhook);
			return webHookPagarMeTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PagarmeException("Ocorreu um erro ao obter o webhook: " + e.getMessage());
		}
	}

}
