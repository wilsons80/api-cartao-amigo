package br.com.cartaoamigo.ws.pagarme.bandeira;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;

@Component
public class BandeiraCartaoRecorrenciaServiceImpl implements BandeiraCartaoRecorrenciaService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public BandeiraCartaoTO getBandeiraCartao(String binCartao) throws Exception {
		return httpRestUtil.get(appId, null, provider.getUrlBandeira(binCartao), BandeiraCartaoTO.class);
	}
}
