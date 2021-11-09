package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{
	
	
	public Optional<Usuarios> findByUsername(String username);

	
	@Query(value = "SELECT u FROM Usuarios u                      "
			+ " inner join PessoaFisica pf on pf = u.pessoaFisica "
			+ " where u.username = ?1                             ")
	public Optional<Usuarios> findByUsernameUsuario(String username);
	
	
	@Query(value = "SELECT u FROM Usuarios u                      "
			+ " inner join PessoaFisica pf on pf = u.pessoaFisica "
			+ " where Upper(u.username) = Upper(?2)               "
			+ "   and u.id != ?1                                   ")
	public Optional<Usuarios> findDuplicateByUsernameUsuario(Long idUsuario, String username);
	
	@Query(value = "SELECT u FROM Usuarios u                           "
			+ " inner join TipoAcessoUsuario ta on ta.idUsuario = u.id "
			+ " inner join TipoUsuario tu on tu.id = ta.idTipoUsuario  "
			+ " where tu.id in ?1                                      "
			+ " order by u.username                                    ")
	public Optional<List<Usuarios>> findAllByTipoUsuario(List<Long> idsTipoUsuario);
 

	@Query(value = "SELECT u FROM Usuarios u                             "
			+ " inner join TipoAcessoUsuario tau on tau.idUsuario = u.id "
			+ " inner join PessoaFisica pf on pf = u.pessoaFisica        "
			+ " where pf.id = ?1                                         "
			+ "  and tau.idTipoUsuario = ?2                              ")
	public Optional<Usuarios> findByIdPessoaFisica(Long idPessoaFisica, Long idTipoUsuario);
	
	@Query(value = "SELECT u FROM Usuarios u              "
			+ " inner join PessoaFisica pf on pf = u.pessoaFisica "
			+ " where pf.email = ?1                          ")
	public Optional<List<Usuarios>> findByEmail(String email);
	
	@Query(value = "SELECT u FROM Usuarios u                                                      "
			+ " inner join PessoaFisica pf on pf = u.pessoaFisica                                 "
			+ " inner join TipoAcessoUsuario ta on ta.idUsuario = u.id                            "
			+ " inner join TipoUsuario tu on tu.id = ta.idTipoUsuario                             "
			+ "  where 1=1                                                                        "
			+ "    and (?2 = true) or tu.id in (1,2,4)                                            "
			+ "    and (?1 is null or upper(pf.nome) like upper('%?1%') )                         "
			+ " order by pf.nome asc                                                              ")
	public Optional<List<Usuarios>> findByFilter(String nome, Boolean acessoRoot);

}
