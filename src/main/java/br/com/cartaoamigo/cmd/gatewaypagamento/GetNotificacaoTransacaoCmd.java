package br.com.cartaoamigo.cmd.gatewaypagamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.NotificacaoTransacaoTOBuilder;
import br.com.cartaoamigo.dao.repository.NotificacaoTransacaoRepository;
import br.com.cartaoamigo.entity.NotificacaoTransacao;
import br.com.cartaoamigo.exception.NotificacaoPagSeguroException;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;
import br.com.cartaoamigo.ws.pagseguro.split.consultarnotificacao.ConsultarNotificacaoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoGatewayTO;

@Component
public class GetNotificacaoTransacaoCmd {
	
	@Autowired private NotificacaoTransacaoRepository repository;
	@Autowired private NotificacaoTransacaoTOBuilder toBuilder;
	@Autowired private ConsultarNotificacaoSplitService consultarNotificacao;
	
	public NotificacaoTransacaoGatewayTO getNotificacaoByPagSeguro(String codigoNotificacao) {
		try {
			NotificacaoTransacaoGatewayTO notificacao = consultarNotificacao.getNotificacao(codigoNotificacao);
			return notificacao;
		} catch (Exception e) {
			throw new NotificacaoPagSeguroException("Não foi possível obter a notificação:" + codigoNotificacao);
		}
	}
	
	public NotificacaoTransacaoTO findByNumerotransacao(String numerotransacao) {
		Optional<NotificacaoTransacao> entity = repository.findByNumerotransacao(numerotransacao);
		if(entity.isPresent()) {
			return toBuilder.buildTO(entity.get());
		}
		return null;
	}
	
	public NotificacaoTransacao getByNumerotransacao(String numerotransacao) {
		Optional<NotificacaoTransacao> entity = repository.findByNumerotransacao(numerotransacao);
		return entity.orElse(null);
	}
	
	public NotificacaoTransacaoTO findByCodigoNotificacao(String codigoNotificacao) {
		Optional<NotificacaoTransacao> entity = repository.findByCodigoNotificacao(codigoNotificacao);
		return toBuilder.buildTO(entity.get());
	}
	
	public NotificacaoTransacao getByCodigoNotificacao(String codigoNotificacao) {
		Optional<NotificacaoTransacao> entity = repository.findByCodigoNotificacao(codigoNotificacao);
		return entity.orElse(null);
	}

}
