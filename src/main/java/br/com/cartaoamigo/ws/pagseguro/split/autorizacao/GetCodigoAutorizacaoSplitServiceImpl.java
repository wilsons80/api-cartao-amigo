package br.com.cartaoamigo.ws.pagseguro.split.autorizacao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoCorretorTO;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoTO;

@Component
public class GetCodigoAutorizacaoSplitServiceImpl implements GetCodigoAutorizacaoSplitService {
	
	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public CodigoAutorizacaoTO getCodigoAutorizacao(CodigoAutorizacaoCorretorTO dados) throws Exception {
		
		HashMap<String, String> parametrosHeader = new HashMap<>();

		return httpRestUtil.getXmlParaObjeto(pagSeguroProvider.getUrlConsultarAutorizacao(dados.getNotificationCode()), CodigoAutorizacaoTO.class, parametrosHeader);
	}

}
