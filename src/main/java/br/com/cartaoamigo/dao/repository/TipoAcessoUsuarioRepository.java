package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.TipoAcessoUsuario;


@Repository
public interface TipoAcessoUsuarioRepository extends JpaRepository<TipoAcessoUsuario, Long>{
	
	@Query("select tau from TipoAcessoUsuario tau                       "
			+ " inner join Usuarios u on u.id = tau.idUsuario           "
			+ " inner join TipoUsuario tu on tu.id = tau.idTipoUsuario  "
			+ " where 1=1                                               "
			+ " and u.username   = ?1                                  "
			+ " and tu.descricao  = ?2                                  ")
	public Optional<TipoAcessoUsuario> findByLoginAndTipoUsuario(String login, String nomeTipoUsuario);
	
	
	
	
	@Query("select tau from TipoAcessoUsuario tau                       "
			+ " inner join Usuarios u on u.id = tau.idUsuario           "
			+ " where 1=1                                               "
			+ " and u.id          = ?1                                  ")
	public Optional<TipoAcessoUsuario> findByUsuario(Long idUsuario);


}
