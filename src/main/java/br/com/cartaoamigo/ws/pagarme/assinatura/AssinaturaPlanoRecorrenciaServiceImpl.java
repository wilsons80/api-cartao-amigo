package br.com.cartaoamigo.ws.pagarme.assinatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPagarMeTO;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;

@Component
public class AssinaturaPlanoRecorrenciaServiceImpl implements AssinaturaPlanoRecorrenciaService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	

	@Override
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(NovaAssinaturaPlanoTO assinaturaTO) throws Exception {
		return httpRestUtil.post(appId, null, provider.getUrlAssinaturaPlano(), assinaturaTO, RetornoAssinaturaPlanoCriadaTO.class);
	}

	@Override
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(NovaAssinaturaPlanoTO assinaturaTO) throws Exception {
		return httpRestUtil.post(appId, null, provider.getUrlAssinaturaPlano(), assinaturaTO, RetornoAssinaturaPlanoCriadaTO.class);
	}

	@Override
	public RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(String idAssinatura) throws Exception {
		return httpRestUtil.delete(appId, null, provider.getUrlCancelarAssinaturaPlano(idAssinatura), null , RetornoAssinaturaPlanoCanceladaTO.class);
	}

	@Override
	public AssinaturaPagarMeTO listarAssinaturasCliente(String idCliente) throws Exception {
		return httpRestUtil.get(appId, null, provider.getUrlListarAssinaturasCliente(idCliente), AssinaturaPagarMeTO.class);
	}
}
