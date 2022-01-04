package br.com.cartaoamigo.ws.pagarme.cobranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.CobrancaFaturaTO;

@Component
public class CobrancaFaturaAssinaturaPlanoRecorrenciaServiceImpl implements CobrancaFaturaAssinaturaPlanoRecorrenciaService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	

	@Override
	public CobrancaFaturaTO getCobrancaFaturasDaAssinatura(String idCobranca) throws Exception {
		return httpRestUtil.get(appId, null, provider.getUrlCobrancaFatura(idCobranca), CobrancaFaturaTO.class);
	}
}
