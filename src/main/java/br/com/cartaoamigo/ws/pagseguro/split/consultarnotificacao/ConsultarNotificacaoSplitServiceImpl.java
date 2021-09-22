package br.com.cartaoamigo.ws.pagseguro.split.consultarnotificacao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoPagSeguroTO;


@Component
public class ConsultarNotificacaoSplitServiceImpl implements ConsultarNotificacaoSplitService {

	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public NotificacaoTransacaoPagSeguroTO getNotificacao(String codigoNotificacao) throws Exception {
		String url = pagSeguroProvider.getUrlConsultarNotificacao(codigoNotificacao);
		
		HashMap<String, String> parametrosHeader = new HashMap<>();

		return httpRestUtil.getXmlParaObjeto(url, NotificacaoTransacaoPagSeguroTO.class, parametrosHeader);
	}

}
