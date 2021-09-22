package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
	
	@Query("select fp from FormaPagamento fp "
			+ " where 1=1                    "
			+ " and fp.nome = ?1             ")
	Optional<FormaPagamento> findByNome(String nome);
	

}
