package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.HistoricoPagamentoTOBuilder;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;

@Component
public class GetHistoricoPagamentoCmd {
	
	@Autowired private HistoricoPagamentoRepository repository;
	@Autowired private HistoricoPagamentoTOBuilder toBuilder;

	public Optional <List<HistoricoPagamento>> getAllByIdTitular(Long idTitular) {
		Optional <List<HistoricoPagamento>> historico= repository.findAllByIdTitular(idTitular);
		return historico;
	}

	
	public List<HistoricoPagamentoTO> getAllTOByIdTitular(Long idTitular) {
		Optional <List<HistoricoPagamento>> historico= repository.findAllByIdTitular(idTitular);
		if(historico.isPresent()) {
			return toBuilder.buildAll(historico.get());
		}
		return new ArrayList<HistoricoPagamentoTO>();
	}
	
	public HistoricoPagamentoTO getTOById(Long id) {
		Optional<HistoricoPagamento> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Histórico de pagamento não encontrado");
		}
		return toBuilder.buildTO(entityOptional.get());		
	}
	
	
	public HistoricoPagamentoTO getUltimoPagamento(Long idTitular) {
		List<HistoricoPagamentoTO> historicosPagamento = getAllTOByIdTitular(idTitular);
		if(Objects.nonNull(historicosPagamento) && !historicosPagamento.isEmpty()) {
			return historicosPagamento.stream().findFirst().orElseGet(null);
		}
		return null;
	}

	public boolean isPossuiPagamentoAprovado(Long idTitular) {
		Optional<List<HistoricoPagamento>> historicosPagamento = getAllByIdTitular(idTitular);
		if(historicosPagamento.isPresent()) {
			long count = historicosPagamento.get()
					                        .stream()
					                        .filter(pag -> pag.getStatusTransacao().getCodigoTransacao().equals("paid")
					                        		       ||
					                        		       pag.getStatusTransacao().getCodigoTransacao().equals("active")					                        		       
					                        		       ).count();
			return count > 0;
		}
		return false;
	}
}
