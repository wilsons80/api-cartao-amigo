package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirHistoricoPagamento {
	
	@Autowired private HistoricoPagamentoRepository repository;
	
	public void excluir(Long idHistoricoPagamento) {
		if(Objects.isNull(idHistoricoPagamento)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o Histórico. Parâmetro ausente.");
		}
		
		repository.deleteById(idHistoricoPagamento);
	}
}
