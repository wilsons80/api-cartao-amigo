package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.EnvioEmail;

@Repository
public interface EnvioEmailRepository extends JpaRepository<EnvioEmail, Long>{
	
	
	@Query("select e from EnvioEmail e  "
			+ " where e.enviado = false ")
	public Optional<List<EnvioEmail>> findAllNaoEnviados();
	
	@Query("select e from EnvioEmail e                            "
			+ " inner join PessoaFisica pf on pf = e.pessoaFisica "
			+ " where pf.id = ?1                                  ")
	public Optional<List<EnvioEmail>> findAllByPessoaFisica(Long idPessoaFisica);

	
	@Query("select e from EnvioEmail e                            "
			+ " inner join PessoaFisica pf on pf = e.pessoaFisica "
			+ " where pf.id = ?1                                  "
			+ "   and e.enviado = false                           ")
	public Optional<List<EnvioEmail>> findAllNaoEnviados(Long idPessoaFisica);


	@Query("select e from EnvioEmail e                            "
			+ " inner join PessoaFisica pf on pf = e.pessoaFisica "
			+ " where pf.id = ?1                                  "
			+ "  and e.idTipoEmail = ?2                           "
			+ "  and e.enviado = false                            ")
	public Optional<List<EnvioEmail>> findAllNaoEnviados(Long idPessoaFisica, Long idTipoEmail);

}
