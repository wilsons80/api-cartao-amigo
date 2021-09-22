package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.AutorizacaoGatewayTOBuilder;
import br.com.cartaoamigo.dao.repository.AutorizacaoGatewayRepository;
import br.com.cartaoamigo.entity.AutorizacaoGateway;
import br.com.cartaoamigo.to.AutorizacaoGatewayTO;
import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoTO;

@Component
public class SalvarAutorizacaoGatewayCmd {
	
	@Autowired private AutorizacaoGatewayRepository repository;
	@Autowired private AutorizacaoGatewayTOBuilder toBuilder;

	public AutorizacaoGatewayTO salvar(AutorizacaoTO dados) {
		AutorizacaoGateway entity = new AutorizacaoGateway();
		entity.setDataAutorizacao(LocalDateTime.now());
		entity.setCodigoAutorizacao(dados.getCodigoAutorizacao());
		entity.setUrl(dados.getUrlAutorizacao());
		
		entity = repository.save(entity);
		return toBuilder.buildTO(entity);
			
	}
}
