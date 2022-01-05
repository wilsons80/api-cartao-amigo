package br.com.cartaoamigo.service.gateway;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import br.com.cartaoamigo.cmd.GetGatewayPagamentoCmd;
import br.com.cartaoamigo.cmd.gateway.GetStatusTransacaoCmd;
import br.com.cartaoamigo.exception.NotificacaoPagSeguroException;
import br.com.cartaoamigo.to.GatewayPagamentoTO;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;
import br.com.cartaoamigo.to.pagarme.NotificacaoPagarmeTransacaoTO;

@Component
public class NotificacaoBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoBuilder.class);
	@Autowired private GetStatusTransacaoCmd getStatusTransacaoCmd;
	@Autowired private GetGatewayPagamentoCmd getGatewayPagamentoCmd;

	
	public NotificacaoTransacaoTO buildPagarMe(NotificacaoPagarmeTransacaoTO notificacao) {
		NotificacaoTransacaoTO notificacaoTO = new NotificacaoTransacaoTO();
		try {
			notificacaoTO.setId(null);
			notificacaoTO.setCodigoNotificacao(notificacao.getId());
			notificacaoTO.setDtNotificacao(LocalDateTime.now());
			notificacaoTO.setNumeroTransacao(notificacao.getData().getCode());
			
			LOGGER.info("Buscando o ID da assinatura: " + notificacao.getData().getInvoice().getSubscriptionId());
			notificacaoTO.setIdAssinaturaPagarme(notificacao.getData().getInvoice().getSubscriptionId());

			notificacaoTO.setQuantidadeNotificacao(1L);
			if(StringUtils.isNotEmpty(notificacao.getAttempts())) {
				String[] tentativas = notificacao.getAttempts().split("/");
				notificacaoTO.setQuantidadeNotificacao(Long.valueOf(tentativas[0]));	
			}
			
			
			GatewayPagamentoTO gatewayPagamentoTO = getGatewayPagamentoCmd.getByCodigo("PAGARME");
			
			LOGGER.info("Buscando o status da transação: " + notificacao.getData().getStatus() + " - " + gatewayPagamentoTO.getId());
			
			StatusTransacaoGatewayPagamentoTO statusTO = getStatusTransacaoCmd.getByStatusAndGateway(notificacao.getData().getStatus(), gatewayPagamentoTO.getId());
			notificacaoTO.setStatus(statusTO);

			return notificacaoTO;

		} catch (Exception e) {
			LOGGER.info("=====================================================================");
			LOGGER.info("Erro ao recuperar dados da notividação do PAGARME");
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			LOGGER.info("=====================================================================");

			throw new NotificacaoPagSeguroException("Erro ao recuperar dados da notividação da PAGARME - Pedido: " + notificacaoTO.getCodigoNotificacao());
		}
	}

	public NotificacaoTransacaoTO buildPagSeguro(MultiValueMap<String, String> paramMap) {
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

			throw new NotificacaoPagSeguroException("Erro ao recuperar dados da notividação da PAGSEGURO - Pedido: "
					+ notificacaoTO.getCodigoNotificacao());
		}

	}

}