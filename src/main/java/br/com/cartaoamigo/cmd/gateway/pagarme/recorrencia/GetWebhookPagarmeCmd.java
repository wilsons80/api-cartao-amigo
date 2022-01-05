package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.to.WebHookPagarMeTO;
import br.com.cartaoamigo.ws.pagarme.webhook.WebhookPagarMeService;


@Component
public class GetWebhookPagarmeCmd {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetWebhookPagarmeCmd.class);
	@Autowired private WebhookPagarMeService service;
	
	public WebHookPagarMeTO getWebhook(String idWebhook) {
		try {
			LOGGER.info("Buscando webhook: " + idWebhook);
			WebHookPagarMeTO webHookPagarMeTO = service.getWebhook(idWebhook);
			
			LOGGER.info("Webhook encontrado: " + webHookPagarMeTO.toString());
			
			return webHookPagarMeTO;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PagarmeException("Ocorreu um erro ao obter o webhook: " + e.getMessage());
		}
	}

}
