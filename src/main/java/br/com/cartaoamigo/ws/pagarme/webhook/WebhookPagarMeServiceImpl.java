package br.com.cartaoamigo.ws.pagarme.webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.WebHookPagarMeTO;

@Component
public class WebhookPagarMeServiceImpl implements WebhookPagarMeService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;


	@Override
	public WebHookPagarMeTO getWebhook(String idWebhook) throws Exception  {
		return httpRestUtil.get(appId, null, provider.getUrlWebHook(idWebhook), WebHookPagarMeTO.class);
	}
}
