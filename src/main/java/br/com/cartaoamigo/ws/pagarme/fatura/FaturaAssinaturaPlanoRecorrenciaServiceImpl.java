package br.com.cartaoamigo.ws.pagarme.fatura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.FaturaAssinaturaPlanoTO;

@Component
public class FaturaAssinaturaPlanoRecorrenciaServiceImpl implements FaturaAssinaturaPlanoRecorrenciaService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	


	@SuppressWarnings("unchecked")
	@Override
	public List<FaturaAssinaturaPlanoTO> getFaturasDaAssinatura(String idCliente, String idAssinatura) {
		return httpRestUtil.get(appId, null, provider.getUrlListarFaturasAssinaturaCliente(idCliente, idAssinatura), List.class);
	}
}
