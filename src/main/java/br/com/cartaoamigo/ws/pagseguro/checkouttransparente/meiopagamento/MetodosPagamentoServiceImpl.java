package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.meiopagamento;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.PagSeguroProvider;
import br.com.cartaoamigo.ws.pagseguro.to.MetodosPagamentoTO;

@Component
public class MetodosPagamentoServiceImpl implements MetodosPagamentoService {

	@Autowired private PagSeguroProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public MetodosPagamentoTO getMetodosPagamento(String idSessao, Double valor) throws Exception {
		String url = pagSeguroProvider.getUrlMetodosPagamento(idSessao, valor);
		
		HashMap<String, String> parametrosHeader = new HashMap<>();
		parametrosHeader.put("Accept", "application/vnd.pagseguro.com.br.v1+xml;charset=ISO-8859-1");

		return httpRestUtil.getXmlParaObjeto(url, MetodosPagamentoTO.class, parametrosHeader);
	}

}
