package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.condicoesparcelamento;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.PagSeguroProvider;
import br.com.cartaoamigo.ws.pagseguro.to.CondicoesParcelamentoBandeirasTO;

@Component
public class CondicoesParcelamentoServiceImpl implements CondicoesParcelamentoService {

	@Autowired private PagSeguroProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public CondicoesParcelamentoBandeirasTO getCondicoesParcelamento(String idSessao, 
			                                               Double valor, 
			                                               String bandeiraCartao, 
			                                               Long maxParcelasSemJuros) throws Exception {
		HashMap<String, String> parametrosHeader = new HashMap<>();
		parametrosHeader.put("Content-type", "application/json;charset=UTF-8");
		
		return httpRestUtil.get(pagSeguroProvider.getUrlCondicoesParcelamento(idSessao, valor, bandeiraCartao, maxParcelasSemJuros), CondicoesParcelamentoBandeirasTO.class, parametrosHeader);
	}

}
