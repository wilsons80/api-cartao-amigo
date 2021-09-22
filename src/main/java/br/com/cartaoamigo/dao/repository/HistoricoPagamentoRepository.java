package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.HistoricoPagamento;

@Repository
public interface HistoricoPagamentoRepository extends JpaRepository<HistoricoPagamento, Long>{
	
	@Query(value = " select hp from HistoricoPagamento hp "
			+ " inner join Titular t on t = hp.titular"
			+ " where t.id = ?1 "
			+ " order by hp.dtPagamentoPlanoContratado desc")
	public Optional<List<HistoricoPagamento>> findAllByIdTitular(Long idTitular);
	
	@Query(value = " select hp from HistoricoPagamento hp      "
			+ " inner join PessoaFisica pf on pf = hp.corretor "
			+ " inner join Corretor c on c.pessoaFisica = pf   "
			+ " where c.id = ?1")
	public Optional<List<HistoricoPagamento>> findAllByIdCorretor(Long idCorretor);
	
	
	@Query(value = " select hp from HistoricoPagamento hp "
			+ " where hp.numeroTransacaoGatewayPagamento = ?1 ")
	public Optional<HistoricoPagamento> findByNumeroTransacao(String numeroTransacao);
	
}
