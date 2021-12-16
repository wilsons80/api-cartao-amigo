package br.com.cartaoamigo.ws.pagseguro.split.consultarnotificacao;

import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoGatewayTO;

public interface ConsultarNotificacaoSplitService {
	
	NotificacaoTransacaoGatewayTO getNotificacao(String codigoNotificacao) throws Exception;

}
