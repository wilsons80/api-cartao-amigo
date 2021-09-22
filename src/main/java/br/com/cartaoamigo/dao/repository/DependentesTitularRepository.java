package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.DependentesTitular;

@Repository
public interface DependentesTitularRepository extends JpaRepository<DependentesTitular, Long> {
	
	@Query(value = "select dt from DependentesTitular dt                "
			+ " inner join Titular titular on titular.id = dt.idTitular "
			+ " where titular.id = ?1                                   "
			+ "   and dt.ativo = true                                   "
			+ "   and dt.exclusaoLogica = false                         ")
	public Optional<List<DependentesTitular>> findAllAtivosByTitular(Long idTitular);
	
	
	@Query(value = "select dt from DependentesTitular dt                "
			+ " inner join Titular titular on titular.id = dt.idTitular "
			+ " where titular.id = ?1                                   "
			+ "   and dt.exclusaoLogica = false                         ")
	public Optional<List<DependentesTitular>> findAllByTitular(Long idTitular);	
	
	
	
	@Query(value = "select dt from DependentesTitular dt                "
			+ " inner join Titular titular on titular.id = dt.idTitular "
			+ " where titular.id = ?1                                   ")
	public Optional<List<DependentesTitular>> findAllByTitularSemExclusaoLogica(Long idTitular);	
	
	@Query(value = "select dt from DependentesTitular dt                               "
			+ " inner join Titular titular on titular.id = dt.idTitular                "
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = dt.pessoaFisica "
			+ " where titular.id = ?1                                                  "
			+ "   and pessoaFisica.cpf = ?2                                            "
			+ "   and dt.exclusaoLogica = false                                        ")
	public Optional<DependentesTitular> findByTitular(Long idTitular, String cpfDependente);
	
	
	@Query(value = "select dt from DependentesTitular dt                               "
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = dt.pessoaFisica "
			+ " where pessoaFisica.cpf = ?1                                            "
			+ "   and dt.exclusaoLogica = false                                        ")
	public Optional<List<DependentesTitular>> findAllDependentesAtivosByCPF(String cpfDependente);
	
	@Query(value = "select dt from DependentesTitular dt                               "
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = dt.pessoaFisica "
			+ " where pessoaFisica.cpf = ?1                                            ")
	public Optional<List<DependentesTitular>> findAllDependentesByCPF(String cpfDependente);

	
}
