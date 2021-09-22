package br.com.cartaoamigo.ws.pagseguro.split.autorizacao;

import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoXMLTO;

public interface AutorizacaoSplitService {
	
	AutorizacaoXMLTO getAutorizacao() throws Exception;

}
