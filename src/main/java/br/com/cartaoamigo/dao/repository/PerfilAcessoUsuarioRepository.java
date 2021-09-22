package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.PerfilAcessoUsuario;

@Repository
public interface PerfilAcessoUsuarioRepository extends JpaRepository<PerfilAcessoUsuario, Long>{
	
	
	@Query(value = "select distinct perfil from PerfilAcessoUsuario perfil              "
			+ " inner join GrupoAcesso grupoAcesso on grupoAcesso = perfil.grupoAcesso  "
			+ " inner join GrupoAcessoModulos gam on gam.idGrupoAcesso = grupoAcesso.id "
			+ " inner join Modulo modulo on modulo = gam.modulo                         "
			+ " inner join Usuarios usuario on usuario.id = perfil.idUsuario            "
			+ " where usuario.id = ?1                                                   "
			+ "   and modulo.agrupador = false                                          ")
	public Optional<List<PerfilAcessoUsuario>> findAllComAcessoByUsuario(Long idUsuario);
	
	
	
	@Query(value = "select distinct perfil from PerfilAcessoUsuario perfil              "
			+ " where perfil.idUsuario = ?1                                             ")
	public Optional<List<PerfilAcessoUsuario>> findAllByIdUsuario(Long idUsuario);
	
	
	@Query(value = "select perfil from PerfilAcessoUsuario perfil                      "
			+ " inner join GrupoAcesso grupoAcesso on grupoAcesso = perfil.grupoAcesso "
			+ " where grupoAcesso.id = ?1                                              ")
	public Optional<List<PerfilAcessoUsuario>> findAllByGrupoAcesso(Long idGrupoAcesso);
	
	
	
}
