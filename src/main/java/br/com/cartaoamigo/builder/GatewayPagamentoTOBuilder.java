package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.GatewayPagamento;
import br.com.cartaoamigo.to.GatewayPagamentoTO;

@Component
public class GatewayPagamentoTOBuilder {
	
	public GatewayPagamentoTO buildTO(GatewayPagamento p) {
		GatewayPagamentoTO to = new GatewayPagamentoTO();
		BeanUtils.copyProperties(p,to);
		
		return to;
	}
	
	public GatewayPagamento build(GatewayPagamentoTO p) {
		GatewayPagamento to = new GatewayPagamento();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public List<GatewayPagamento> buildTOAll(List<GatewayPagamentoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<GatewayPagamentoTO> buildAll(List<GatewayPagamento>dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	

}



















