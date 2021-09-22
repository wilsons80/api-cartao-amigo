package br.com.cartaoamigo.ws.pagseguro.split.bandeira;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;


@Component
public class BandeiraCartaoSplitServiceImpl implements BandeiraCartaoSplitService {

	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public BandeiraCartaoTO getBandeira(String idSessao, String binCartao) throws Exception {
	    return httpRestUtil.get(pagSeguroProvider.getUrlBandeira(idSessao, binCartao), BandeiraCartaoTO.class);
	}

}
