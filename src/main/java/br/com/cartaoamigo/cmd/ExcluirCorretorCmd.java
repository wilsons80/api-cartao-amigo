package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.exception.ClinicaNaoEncontradaException;
import br.com.cartaoamigo.exception.CorretorComHistoricoPagamentoException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirCorretorCmd {

	@Autowired private CorretorRepository repository;
	@Autowired private HistoricoPagamentoRepository historicoPagamentoRepository;
	
	
	public void excluir(Long idCorretor) {
		if (Objects.isNull(idCorretor)) {
			throw new ParametroNaoInformadoException("Identificador do corretor não informado.");
		}

		Optional<List<HistoricoPagamento>> pagamentos = historicoPagamentoRepository.findAllByIdCorretor(idCorretor);
		if(pagamentos.isPresent()) {
			throw new CorretorComHistoricoPagamentoException("Não é possível excluir, pois já existem histórico de pagamentos com esse corretor.");
		}
		
		Corretor entity = repository.findById(idCorretor).orElseThrow(() -> new ClinicaNaoEncontradaException("Corretor informado não existe: " + idCorretor));
		repository.delete(entity);
	}

}
