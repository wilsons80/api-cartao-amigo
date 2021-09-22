package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{
	
	Optional<PessoaFisica> findByCpf(String cpf);

	@Query(value = "SELECT pf FROM PessoaFisica pf              "
			+ " where upper(pf.email) = upper(?1)               ")
	public Optional<PessoaFisica> findByEmail(String email);

	@Query(value = "SELECT pf FROM PessoaFisica pf              "
			+ " where upper(pf.email) = upper(?1)               "
			+ "   and pf.cpf   = ?2")
	public Optional<PessoaFisica> findByEmailAndCpf(String email, String cpf);

	
	@Query(value = "SELECT pf FROM PessoaFisica pf              "
			+ " where upper(pf.email) = upper(?1)               "
			+ "   and pf.id != ?2                               ")
	public Optional<PessoaFisica> findByEmailDeOutraPessoaFisica(String email, Long idPessoaFisica);
	
}
