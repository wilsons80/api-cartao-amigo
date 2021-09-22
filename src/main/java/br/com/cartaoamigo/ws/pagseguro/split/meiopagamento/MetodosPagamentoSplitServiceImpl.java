package br.com.cartaoamigo.ws.pagseguro.split.meiopagamento;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.MetodosPagamentoTO;

@Component
public class MetodosPagamentoSplitServiceImpl implements MetodosPagamentoSplitService {

	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public MetodosPagamentoTO getMetodosPagamento(String idSessao, Double valor) throws Exception {
		String url = pagSeguroProvider.getUrlMeiosPagamento(idSessao, valor);
		
		HashMap<String, String> parametrosHeader = new HashMap<>();
		//parametrosHeader.put("Accept", "application/vnd.pagseguro.com.br.v1+json;charset=ISO-8859-1");
		
		
		parametrosHeader.put("Accept", "application/vnd.pagseguro.com.br.v1+xml;charset=ISO-8859-1");
		                                
		return httpRestUtil.getXmlParaObjeto(url, MetodosPagamentoTO.class, parametrosHeader);
	}

}
