package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.HistoricoPagamentoTOBuilder;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.rule.ValidarHistoricoPagamentoRule;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;

@Component
public class CadastrarHistoricoPagamentoCmd {

	@Autowired private HistoricoPagamentoRepository repository;
	@Autowired private HistoricoPagamentoTOBuilder toBuilder;
	@Autowired private ValidarHistoricoPagamentoRule rule;
	
	
	public HistoricoPagamentoTO cadastrar(HistoricoPagamentoTO to) {
		rule.validar(to);
		
		HistoricoPagamento entity = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(entity));
	}
	

}
