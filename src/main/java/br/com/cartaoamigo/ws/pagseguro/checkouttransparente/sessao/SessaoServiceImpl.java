package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.PagSeguroProvider;
import br.com.cartaoamigo.ws.pagseguro.to.SessaoTO;

@Component
public class SessaoServiceImpl implements SessaoService {
	
	@Autowired private PagSeguroProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public SessaoTO getSessao() throws Exception {
		return httpRestUtil.postXML(pagSeguroProvider.getUrlSession(), null, SessaoTO.class);
	}

}
