package br.com.cartaoamigo.cmd.gateway;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.StatusTransacaoGatewayPagamentoTOBuilder;
import br.com.cartaoamigo.dao.repository.StatusTransacaoGatewayPagamentoRepository;
import br.com.cartaoamigo.entity.StatusTransacaoGatewayPagamento;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;

@Component
public class GetStatusTransacaoCmd {
	
	@Autowired private StatusTransacaoGatewayPagamentoRepository repository;
	@Autowired private StatusTransacaoGatewayPagamentoTOBuilder toBuilder;
	
	public StatusTransacaoGatewayPagamentoTO findByID(Long idStatus) {
		Optional<StatusTransacaoGatewayPagamento> entity = repository.findById(idStatus);
		return toBuilder.buildTO(entity.get());
	}

	
	public StatusTransacaoGatewayPagamentoTO getByStatusAndGateway(String codigoTransacao, Long idGateway) {
		Optional<StatusTransacaoGatewayPagamento> entity = repository.findByStatusAndGateway(codigoTransacao, idGateway);
		return toBuilder.buildTO(entity.get());
	}
	
}
