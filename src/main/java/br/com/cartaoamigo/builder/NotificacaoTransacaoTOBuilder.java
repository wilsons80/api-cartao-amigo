package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.NotificacaoTransacao;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;

@Component
public class NotificacaoTransacaoTOBuilder {
	
	@Autowired private StatusTransacaoGatewayPagamentoTOBuilder statusTOBuilder;
	
	public NotificacaoTransacao buildNewEntity(NotificacaoTransacaoTO to, NotificacaoTransacao entity) {
		BeanUtils.copyProperties(to, entity, "id");
		entity.setStatus(statusTOBuilder.build(to.getStatus()));
		return entity;
	} 
	
	public NotificacaoTransacaoTO buildTO(NotificacaoTransacao p) {
		NotificacaoTransacaoTO to = new NotificacaoTransacaoTO();
		BeanUtils.copyProperties(p, to);
	    to.setStatus(statusTOBuilder.buildTO(p.getStatus()));
		return to;
	}
	
	public NotificacaoTransacao build(NotificacaoTransacaoTO p) {
		NotificacaoTransacao to = new NotificacaoTransacao();
		BeanUtils.copyProperties(p, to);
		to.setStatus(statusTOBuilder.build(p.getStatus()));
		
		return to;
	}
	

	public List<NotificacaoTransacao> buildTOAll(List<NotificacaoTransacaoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<NotificacaoTransacaoTO> buildAll(List<NotificacaoTransacao> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}	
	
}


