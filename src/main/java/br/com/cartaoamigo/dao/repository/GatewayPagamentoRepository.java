package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.GatewayPagamento;

@Repository
public interface GatewayPagamentoRepository extends JpaRepository<GatewayPagamento, Long>{

	@Query("select g from GatewayPagamento g "
			+ "where 1=1                     "
			+ " and g.codigo = ?1            ")
	Optional<GatewayPagamento> findByCodigo(String codigo);
}
