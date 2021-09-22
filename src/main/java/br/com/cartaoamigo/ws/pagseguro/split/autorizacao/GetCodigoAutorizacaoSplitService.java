package br.com.cartaoamigo.ws.pagseguro.split.autorizacao;

import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoCorretorTO;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoTO;

public interface GetCodigoAutorizacaoSplitService {
	
	CodigoAutorizacaoTO getCodigoAutorizacao(CodigoAutorizacaoCorretorTO dados) throws Exception;

}
