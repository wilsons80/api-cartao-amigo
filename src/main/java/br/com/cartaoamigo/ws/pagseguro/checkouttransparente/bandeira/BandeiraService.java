package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.bandeira;

import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;

public interface BandeiraService {
	
	BandeiraCartaoTO getBandeira(String idSessao, String binCartao) throws Exception;

}
