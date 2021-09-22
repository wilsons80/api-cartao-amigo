package br.com.cartaoamigo.ws.pagseguro.split.sessao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.SessaoTO;

@Component
public class SessaoSplitServiceImpl implements SessaoSplitService {
	
	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public SessaoTO getSessao() throws Exception {
		return httpRestUtil.postXML(pagSeguroProvider.getUrlSession(), null, SessaoTO.class);
	}

}
