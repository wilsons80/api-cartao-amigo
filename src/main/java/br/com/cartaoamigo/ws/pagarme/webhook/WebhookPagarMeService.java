package br.com.cartaoamigo.ws.pagarme.webhook;

import br.com.cartaoamigo.ws.pagarme.to.WebHookPagarMeTO;

public interface WebhookPagarMeService {
	
	WebHookPagarMeTO getWebhook(String idWebhook) throws Exception ;
	
}
