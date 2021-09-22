package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.StatusTransacaoGatewayPagamento;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;

@Component
public class StatusTransacaoGatewayPagamentoTOBuilder {
	
	public StatusTransacaoGatewayPagamentoTO buildTO(StatusTransacaoGatewayPagamento p) {
		StatusTransacaoGatewayPagamentoTO to = new StatusTransacaoGatewayPagamentoTO();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public StatusTransacaoGatewayPagamento build(StatusTransacaoGatewayPagamentoTO p) {
		StatusTransacaoGatewayPagamento to = new StatusTransacaoGatewayPagamento();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	

	public List<StatusTransacaoGatewayPagamento> buildTOAll(List<StatusTransacaoGatewayPagamentoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<StatusTransacaoGatewayPagamentoTO> buildAll(List<StatusTransacaoGatewayPagamento> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
