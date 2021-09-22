package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Titular;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long> {
	
	@Query(value = "select t from Titular t 												"
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = t.pessoaFisica		"
			+ " where pessoaFisica.id = ?1								 					")
	public Optional<Titular> findByPessoaFisica(Long idPessoaFisica);

	
	@Query(value = "select t from Titular t 												"
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = t.pessoaFisica		"
			+ " inner join Usuarios u on u.pessoaFisica = pessoaFisica                       "
			+ " where u.id = ?1			            					 					")
	public Optional<Titular> findTitularByIdUsuarioTitular(Long idUsuario);

	@Query(value = "select distinct t from Titular t 												"
			+ " inner join DependentesTitular d on d.idTitular = t.id                            "
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = d.pessoaFisica		"
			+ " inner join Usuarios u on u.pessoaFisica = pessoaFisica                      "
			+ " where u.id = ?1			            					 					")
	public Optional<Titular> findTitularByIdUsuarioDependente(Long idUsuario);

	
	
	@Query(value = "select t from Titular t 												"
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = t.pessoaFisica		"
			+ " where pessoaFisica.cpf = ?1								 					")
	public Optional<Titular> findByCPF(String cpf);


	
	@Query(value = "select t from Titular t 												"
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = t.pessoaFisica		"
			+ " where pessoaFisica.email = ?1								 		        ")
	public Optional<Titular> findByEmail(String email);

	
	@Query(value = "select t from Titular t 												"
			+ " inner join PessoaFisica pessoaFisica on pessoaFisica = t.pessoaFisica		"
			+ " where pessoaFisica.email = ?1								 		        "
			+ "   and pessoaFisica.cpf != ?2                                                ")
	public Optional<Titular> findByEmailOutroCpf(String email, String cpf);
}
