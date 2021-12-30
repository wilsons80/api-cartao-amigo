package br.com.cartaoamigo.ws.pagarme.tokencartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.RequestCardTokenCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.RequestTokenCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.TokenCartaoTO;

@Component
public class TokenCartaoRecorrenciaServiceImpl implements TokenCartaoRecorrenciaService{

	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public TokenCartaoTO getTokenCartao(String numeroCartao,
							            String nomeImpresso, 
							            String bandeiraCartao, 
							            String cvv, 
							            String mesVencimentoCartao, 
							            String anoVencimentoCartao) throws Exception {
		
		RequestTokenCartaoTO requestTO = new RequestTokenCartaoTO();
		requestTO.setType("card");
		requestTO.setCard(new RequestCardTokenCartaoTO(numeroCartao, nomeImpresso, mesVencimentoCartao, anoVencimentoCartao, cvv, bandeiraCartao));
		
		return httpRestUtil.post(provider.getUrlTokenCartao(), requestTO, TokenCartaoTO.class);
	}
}
