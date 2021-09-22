package br.com.cartaoamigo.builder;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.AutorizacaoGateway;
import br.com.cartaoamigo.to.AutorizacaoGatewayTO;
import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoTO;

@Component
public class AutorizacaoGatewayTOBuilder {
	
	public AutorizacaoTO buildAutorizacaoTO(AutorizacaoGateway p) {
		AutorizacaoTO to = new AutorizacaoTO();
		to.setCodigoAutorizacao(p.getCodigoAutorizacao());
		to.setDataAutorizacao(p.getDataAutorizacao());
		to.setId(p.getId());
		to.setUrlAutorizacao(p.getUrl());
		return to;
	}
	
	public AutorizacaoGatewayTO buildTO(AutorizacaoGateway p) {
		AutorizacaoGatewayTO to = new AutorizacaoGatewayTO();
		BeanUtils.copyProperties(p, to);
		return to;
	}
	
	public AutorizacaoGateway build(AutorizacaoGatewayTO p) {
		AutorizacaoGateway to = new AutorizacaoGateway();
		BeanUtils.copyProperties(p, to);
		return to;
	}
	
	public List<AutorizacaoGateway> buildTOAll(List<AutorizacaoGatewayTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<AutorizacaoGatewayTO> buildAll(List<AutorizacaoGateway> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<AutorizacaoTO> buildAllAutorizacaoTO(List<AutorizacaoGateway> dtos){
		return dtos.stream().map(dto -> buildAutorizacaoTO(dto)).collect(Collectors.toList());
	}
	
}
