package br.com.cartaoamigo.cmd.gateway.pagarme;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.HistoricoPagamentoTOBuilder;
import br.com.cartaoamigo.builder.NotificacaoTransacaoTOBuilder;
import br.com.cartaoamigo.builder.StatusTransacaoGatewayPagamentoTOBuilder;
import br.com.cartaoamigo.cmd.GravarEnvioEmailCmd;
import br.com.cartaoamigo.cmd.SalvarValidadeCartaoCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetWebhookPagarmeCmd;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.dao.repository.NotificacaoTransacaoRepository;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.entity.NotificacaoTransacao;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.service.gateway.NotificacaoBuilder;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;
import br.com.cartaoamigo.to.pagarme.NotificacaoPagarmeTransacaoTO;
import br.com.cartaoamigo.ws.pagarme.to.WebHookPagarMeTO;

@Component
public class SalvarNotificacaoPagarMeTransacaoCmd {
	
	@Autowired private NotificacaoTransacaoRepository repository;
	@Autowired private NotificacaoTransacaoTOBuilder toBuilder;
	@Autowired private StatusTransacaoGatewayPagamentoTOBuilder statusTOBuilder;
	@Autowired private NotificacaoBuilder notificacaoBuilderCmd;
	@Autowired private HistoricoPagamentoRepository historicoPagamentoRepository;
	@Autowired private HistoricoPagamentoTOBuilder historicoPagamentoTOBuilder;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	@Autowired private SalvarValidadeCartaoCmd salvarValidadeCartaoCmd;
	@Autowired private GetWebhookPagarmeCmd getWebhookPagarmeCmd;
	
	public void salvar(NotificacaoPagarmeTransacaoTO notificacao) {
		WebHookPagarMeTO webHookPagarMeTO = getWebhookPagarmeCmd.getWebhook(notificacao.getId());
		
		NotificacaoTransacaoTO notificacaoTransacaoTO = notificacaoBuilderCmd.buildPagarMe(webHookPagarMeTO);		
		NotificacaoTransacao notificacaoTransacao = repository.save(toBuilder.build(notificacaoTransacaoTO));		
		salvarHistoricoPagamento(toBuilder.buildTO(notificacaoTransacao), webHookPagarMeTO.getEvent());
	}
	
	
	private NotificacaoTransacaoTO salvarHistoricoPagamento(NotificacaoTransacaoTO to, String eventoNotificacao) {
		NotificacaoTransacao notificacaoGateWay = null;
		Optional<NotificacaoTransacao> notificacao = repository.findByCodigoNotificacao(to.getCodigoNotificacao());
		if(notificacao.isPresent()) {
			notificacaoGateWay = toBuilder.buildNewEntity(to, notificacao.get());
		} else {
			notificacaoGateWay = toBuilder.build(to);
		}
		
		notificacaoGateWay.setStatus               (statusTOBuilder.build(to.getStatus()));
		notificacaoGateWay.setQuantidadeNotificacao(notificacaoGateWay.getQuantidadeNotificacao()+1);	
		notificacaoGateWay.setDtNotificacao        (LocalDateTime.now());
		
		NotificacaoTransacaoTO notificacaoTransacaoTO = toBuilder.buildTO(repository.save(notificacaoGateWay));
		
		Optional<HistoricoPagamento> historicoPagamento = historicoPagamentoRepository.findByNumeroTransacao(notificacaoTransacaoTO.getIdAssinaturaPagarme());
		if(historicoPagamento.isPresent()) {	
			String codigoTransacaoAntes = historicoPagamento.get().getStatusTransacao().getCodigoTransacao();
			
			historicoPagamento.get().setStatusTransacao(notificacaoGateWay.getStatus());
			HistoricoPagamento pagamento = historicoPagamentoRepository.save(historicoPagamento.get());			
			HistoricoPagamentoTO historicoPagamentoTO = historicoPagamentoTOBuilder.buildTO(pagamento);
			
			//pagamento estava aprovado e chegou uma notificação cancelando o pagamento
			if(isTransacaoAprovada(eventoNotificacao, codigoTransacaoAntes) && 
			   isTransacaoNaoAprovada(eventoNotificacao, notificacaoGateWay.getStatus().getCodigoTransacao()) ) {
				
				salvarValidadeCartaoCmd.decrementarValidade(historicoPagamento.get().getTitular().getPessoaFisica().getId(), 
						                                    historicoPagamento.get().getTipoPlano().getQuantidadeParcelas().intValue(), 
						                                    historicoPagamento.get().getTitular().getId());
			}
			
			if(isTransacaoAprovada(eventoNotificacao, notificacaoGateWay.getStatus().getCodigoTransacao()) ) {
				
				salvarValidadeCartaoCmd.incrementarValidade(pagamento.getTitular().getPessoaFisica().getId(), historicoPagamentoTO.getTipoPlano().getQuantidadeDiasVigencia().intValue());
				
				/////////////////////////////////////////////////////////////////////////////////
				//Enviar email de pagamento >>> Pago
				/////////////////////////////////////////////////////////////////////////////////
				EnvioEmailTO envioEmailTO = new EnvioEmailTO();
				envioEmailTO.setIdTipoEmail         (TipoEmail.PAGAMENTO.getId());
				envioEmailTO.setPessoaFisica        (historicoPagamentoTO.getTitular().getPessoaFisica());	
				envioEmailTO.setIdTipoPlano         (historicoPagamentoTO.getTipoPlano().getId());
				envioEmailTO.setIsTitular           (true);
				envioEmailTO.setIdHistoricoPagamento(historicoPagamentoTO.getId());
				
				gravarEnvioEmailCmd.gravarEnvioEmail(envioEmailTO);
			}
		}

		return notificacaoTransacaoTO;
	}


	private boolean isTransacaoAprovada(String eventoNotificacao, String codigoTransacao) {
		return "charge.paid".equals(eventoNotificacao) && "paid".equals(codigoTransacao.toLowerCase());
	}
	
	private boolean isTransacaoNaoAprovada(String eventoNotificacao, String codigoTransacao) {
		return !isTransacaoAprovada(eventoNotificacao, codigoTransacao);
	}

	
}