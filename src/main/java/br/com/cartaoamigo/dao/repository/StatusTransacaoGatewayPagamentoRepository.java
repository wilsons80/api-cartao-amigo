package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.StatusTransacaoGatewayPagamento;

@Repository
public interface StatusTransacaoGatewayPagamentoRepository extends JpaRepository<StatusTransacaoGatewayPagamento, Long> {

	@Query("select s from StatusTransacaoGatewayPagamento s             "
			+ " inner join GatewayPagamento g on g = s.gatewayPagamento "
			+ " where 1=1                                               "
			+ "   and s.codigoTransacao = ?1                            "
			+ "   and g.id = ?2                                         ")
	Optional<StatusTransacaoGatewayPagamento> findByStatusAndGateway(Long codigoTransacao, Long idGateway);
	
}
