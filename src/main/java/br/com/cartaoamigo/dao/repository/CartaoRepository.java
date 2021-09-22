package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cartaoamigo.entity.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao,Long>{
	
	
	@Query(value = "select c from Cartao c "
			+ " where upper(c.numeroCartao) = upper(?1)" )
	public Optional<Cartao> findByNumeroCartao(String numeroCartao);

	@Query(value = "select c from Cartao c                        "
			+ " inner join PessoaFisica pf on pf = c.pessoaFisica "
			+ " where pf.id = ?1                                  "
			+ "   and c.isTitular = true                          " )
	public Optional<Cartao> findCartaoTitularByIdPessoaFisica(Long idPessoaFisica);

	
	@Query(value = "select c from Cartao c                        "
			+ " inner join PessoaFisica pf on pf = c.pessoaFisica "
			+ " where pf.id = ?1                                  "
			+ "   and c.isTitular = false                          " )
	public Optional<Cartao> findCartaoDependenteByIdPessoaFisica(Long idPessoaFisica);
	
	
	@Query(value = "select c from Cartao c "
			+ " where c.idTitular = ?1" )
	public Optional<List<Cartao>> findAllByTitular(Long idTitular);

	@Query(value = "select c from Cartao c "
			+ " where c.idTitular = ?1     "
			+ "   and c.isTitular = true   " )
	public Optional<Cartao> findByTitular(Long idTitular);

	
	@Query(value = "select c from Cartao c "
			+ " where c.ativo = ?1" )
	public Optional<List<Cartao>> findAllByStatus(Boolean status);
	
	
	@Query(value = "select c from Cartao c                        "
			+ " inner join PessoaFisica pf on pf = c.pessoaFisica "
			+ " where 1=1                                         "
			+ "  and pf.cpf = ?1                                  "
			+ "  and c.isTitular = ?2                             " )
	public Optional<List<Cartao>> findAllByCpf(String cpf, boolean isTitular);
	
}
