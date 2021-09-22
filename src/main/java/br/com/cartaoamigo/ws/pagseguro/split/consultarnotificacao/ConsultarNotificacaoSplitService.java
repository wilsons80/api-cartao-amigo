package br.com.cartaoamigo.ws.pagseguro.split.consultarnotificacao;

import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoPagSeguroTO;

public interface ConsultarNotificacaoSplitService {
	
	NotificacaoTransacaoPagSeguroTO getNotificacao(String codigoNotificacao) throws Exception;

}
