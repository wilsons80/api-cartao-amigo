package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GatewayPagamentoTOBuilder;
import br.com.cartaoamigo.dao.repository.GatewayPagamentoRepository;
import br.com.cartaoamigo.to.GatewayPagamentoTO;

@Component
public class GetGatewayPagamentoCmd {
	@Autowired private GatewayPagamentoRepository repository;
    @Autowired private GatewayPagamentoTOBuilder tobuilder;
    
    public List<GatewayPagamentoTO> getAll() {
    	return tobuilder.buildAll(repository.findAll());
    }
    
    public GatewayPagamentoTO getPorId(Long id) {
    	return tobuilder.buildTO(repository.findById(id).orElse(null));
    }
    
    public GatewayPagamentoTO getByCodigo(String codigo) {
    	return tobuilder.buildTO(repository.findByCodigo(codigo).orElse(null));
    }
    
    
}
