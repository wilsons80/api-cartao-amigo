package br.com.cartaoamigo.ws.pagseguro.split.autorizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoXMLTO;

@Component
public class AutorizacaoSplitServiceImpl implements AutorizacaoSplitService {
	
	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	

	@Value("${pagseguro.split.urlNotificationURL}") private String urlNotificacao;
	@Value("${pagseguro.split.urlRedirectURL}")     private String urlRedirect;
	
	@Override
	public AutorizacaoXMLTO getAutorizacao() throws Exception {
		
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		xml.append("<authorizationRequest>");
		xml.append("<reference>cartaoamigo</reference>");
		xml.append("<permissions>");
		xml.append("<code>CREATE_CHECKOUTS</code>");
		xml.append("<code>RECEIVE_TRANSACTION_NOTIFICATIONS</code>");
		xml.append("<code>SEARCH_TRANSACTIONS</code>");
		xml.append("<code>MANAGE_PAYMENT_PRE_APPROVALS</code>");
		xml.append("<code>DIRECT_PAYMENT</code>");
		xml.append("</permissions>");
		xml.append("<redirectURL>");
		xml.append(urlRedirect);
		xml.append("</redirectURL>");
		xml.append("<notificationURL>");
		xml.append(urlNotificacao);
		xml.append("</notificationURL>");
		xml.append("</authorizationRequest>");
		
		return httpRestUtil.postXML(pagSeguroProvider.getUrlSolicitarAutorizacao(), xml.toString(), AutorizacaoXMLTO.class);
	}

}
