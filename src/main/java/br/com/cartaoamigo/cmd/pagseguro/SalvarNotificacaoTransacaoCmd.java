package br.com.cartaoamigo.cmd.pagseguro;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import br.com.cartaoamigo.builder.HistoricoPagamentoTOBuilder;
import br.com.cartaoamigo.builder.NotificacaoTransacaoTOBuilder;
import br.com.cartaoamigo.builder.StatusTransacaoGatewayPagamentoTOBuilder;
import br.com.cartaoamigo.cmd.GravarEnvioEmailCmd;
import br.com.cartaoamigo.cmd.SalvarValidadeCartaoCmd;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.dao.repository.NotificacaoTransacaoRepository;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.entity.NotificacaoTransacao;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.service.pagseguro.NotificacaoBuilderCmd;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;
import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoPagSeguroTO;

@Component
public class SalvarNotificacaoTransacaoCmd {
	
	@Autowired private NotificacaoTransacaoRepository repository;
	@Autowired private NotificacaoTransacaoTOBuilder toBuilder;
	@Autowired private StatusTransacaoGatewayPagamentoTOBuilder statusTOBuilder;
	@Autowired private NotificacaoBuilderCmd notificacaoBuilderCmd;
	@Autowired private GetNotificacaoTransacaoCmd getNotificacaoTransacaoCmd;
	@Autowired private GetStatusTransacaoCmd getStatusTransacaoCmd;
	@Autowired private HistoricoPagamentoRepository historicoPagamentoRepository;
	@Autowired private HistoricoPagamentoTOBuilder historicoPagamentoTOBuilder;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	@Autowired private SalvarValidadeCartaoCmd salvarValidadeCartaoCmd;
	
	
	public void salvar(MultiValueMap<String,String> paramMap) {
		NotificacaoTransacaoTO notificacaoTransacaoTO = notificacaoBuilderCmd.build(paramMap);
		
		// Busca os dados da transação a partir da notificação
		NotificacaoTransacaoPagSeguroTO notificacaoTransacaoPagSeguroTO = getNotificacaoTransacaoCmd.getNotificacaoByPagSeguro(notificacaoTransacaoTO.getCodigoNotificacao());
		
		StatusTransacaoGatewayPagamentoTO statusTO = getStatusTransacaoCmd.findByID(notificacaoTransacaoPagSeguroTO.getStatus());
		notificacaoTransacaoTO.setStatus(statusTO); 
		notificacaoTransacaoTO.setNumeroTransacao(notificacaoTransacaoPagSeguroTO.getCode());
		notificacaoTransacaoTO.addQuantidadeNotificacao();	
		
		NotificacaoTransacao notificacaoTransacao = repository.save(toBuilder.build(notificacaoTransacaoTO));
		
		salvarHistoricoPagamento(toBuilder.buildTO(notificacaoTransacao));
	}
	
	
	public NotificacaoTransacaoTO salvarHistoricoPagamento(NotificacaoTransacaoTO to) {
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
		
		Optional<HistoricoPagamento> historicoPagamento = historicoPagamentoRepository.findByNumeroTransacao(notificacaoTransacaoTO.getNumeroTransacao());
		if(historicoPagamento.isPresent()) {			
			Long codigoTransacaoAntes = historicoPagamento.get().getStatusTransacao().getCodigoTransacao();
			
			historicoPagamento.get().setStatusTransacao(notificacaoGateWay.getStatus());
			HistoricoPagamento pagamento = historicoPagamentoRepository.save(historicoPagamento.get());			
			HistoricoPagamentoTO historicoPagamentoTO = historicoPagamentoTOBuilder.buildTO(pagamento);
			
			//pagamento estava aprovado e chegou uma notificação cancelando o pagamento
			if(isTransacaoAprovada(codigoTransacaoAntes) && 
			   isTransacaoNaoAprovada(notificacaoGateWay.getStatus().getCodigoTransacao()) ) {
				
				salvarValidadeCartaoCmd.decrementarValidade(historicoPagamento.get().getTitular().getPessoaFisica().getId(), 
						                                    historicoPagamento.get().getTipoPlano().getQuantidadeParcelas().intValue(), 
						                                    historicoPagamento.get().getTitular().getId());
			}
			
			if(isTransacaoAprovada(notificacaoGateWay.getStatus().getCodigoTransacao()) ) {
				
				salvarValidadeCartaoCmd.incrementarValidade(pagamento.getTitular().getPessoaFisica().getId(), historicoPagamentoTO.getTipoPlano().getQuantidadeParcelas().intValue());
				
				/////////////////////////////////////////////////////////////////////////////////
				//Enviar email de pagamento >>> 3: Pago - 4: Disponível
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


	private boolean isTransacaoAprovada(Long codigoTransacao) {
		//3: Pago - 4: Disponível
		return codigoTransacao == 3L || codigoTransacao == 4L;
	}
	
	private boolean isTransacaoNaoAprovada(Long codigoTransacao) {
		//3: Pago - 4: Disponível
		return codigoTransacao != 3L && codigoTransacao != 4L;
	}

	
}