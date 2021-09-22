package br.com.cartaoamigo.service.pagseguro;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import br.com.cartaoamigo.exception.NotificacaoPagSeguroException;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;

@Component
public class NotificacaoBuilderCmd {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(NotificacaoBuilderCmd.class);
	
	public NotificacaoTransacaoTO build(MultiValueMap<String,String> paramMap) {
		NotificacaoTransacaoTO notificacaoTO = new NotificacaoTransacaoTO();
		
		try {
			notificacaoTO.setCodigoNotificacao(paramMap.getFirst("notificationCode"));
			notificacaoTO.setDtNotificacao(LocalDateTime.now());
			
			notificacaoTO.setQuantidadeNotificacao(null);
			notificacaoTO.setId(null);
			notificacaoTO.setNumeroTransacao(null);
			notificacaoTO.setStatus(null);
			
			return notificacaoTO;
			
		} catch (Exception e) {
			LOGGER.info("=====================================================================");
			LOGGER.info("Erro ao recuperar dados da notividação do PAGSEGURO");
			LOGGER.info(e.getMessage());
			LOGGER.info("=====================================================================");
			
			throw new NotificacaoPagSeguroException("Erro ao recuperar dados da notividação da PAGSEGURO - Pedido: " + notificacaoTO.getCodigoNotificacao());
		}
		
	}
	
	
	
}