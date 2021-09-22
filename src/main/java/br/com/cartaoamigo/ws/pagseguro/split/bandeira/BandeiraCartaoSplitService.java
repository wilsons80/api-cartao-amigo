package br.com.cartaoamigo.ws.pagseguro.split.bandeira;

import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;

public interface BandeiraCartaoSplitService {
	
	BandeiraCartaoTO getBandeira(String idSessao, String binCartao) throws Exception;

}
