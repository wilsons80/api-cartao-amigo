package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.tokencartao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.PagSeguroProvider;
import br.com.cartaoamigo.ws.pagseguro.to.TokenCartaoTO;

@Component
public class TokenCartaoServiceImpl implements TokenCartaoService{

	
	@Autowired private PagSeguroProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public TokenCartaoTO getTokenCartao(String idSessao, 
							            Double valor, 
							            String numeroCartao, 
							            String bandeiraCartao, 
							            String cvv, 
							            String mesVencimentoCartao, 
							            String anoVencimentoCartao) throws Exception {
		
		HashMap<String, String> parametrosHeader = new HashMap<>();
		parametrosHeader.put("Content-type", "application/x-www-form-urlencoded");
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("sessionId", idSessao);
		map.add("amount", valor.toString());
		map.add("cardNumber", numeroCartao);
		map.add("cardBrand", bandeiraCartao);
		map.add("cardCvv", cvv);
		map.add("cardExpirationMonth", mesVencimentoCartao);
		map.add("cardExpirationYear", anoVencimentoCartao);
		
		return httpRestUtil.postForm(pagSeguroProvider.getUrlTokenCartao(), map, TokenCartaoTO.class);
	}
}
