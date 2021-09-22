package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Corretor;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {
	
	@Query(value = "select c from Corretor c "
			+ " where c.ativo = ?1" )
	public Optional<List<Corretor>> findAllByStatus(Boolean status);
	
	
	@Query(value = "select c from Corretor c "
			+ " where upper(c.codigo) = upper(?1)" )
	public Optional<Corretor> findByCodigo(String codigo);

	@Query(value = "select c from Corretor c "
			+ " where c.token = ?1" )
	public Optional<Corretor> findByToken(String token);
	
	@Query(value = "select c from Corretor c                       "
			+ " inner join PessoaFisica pf on pf = c.pessoaFisica  "
			+ " where pf.id = ?1                                   " )
	public Optional<Corretor> findByPessoaFisica(Long idPessoaFisica);

}
