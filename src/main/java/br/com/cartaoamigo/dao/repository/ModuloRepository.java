package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{
	
	public Optional<Modulo> findByNome(String nomeModulo);
	
	public List<Modulo> findAllByOrderByDescricaoAsc();
	
	
	@Query("select m from Modulo m "
			+ " where m.nome = ?1 "
			+ " order by m.nome")
	public Optional<Modulo> findPorNome(String nome);
	
	@Query(value = "SELECT distinct m FROM Modulo m                        "
			+ " inner join GrupoAcessoModulos gam on gam.modulo = m        "
			+ " inner join GrupoAcesso ga on ga.id = gam.idGrupoAcesso     "
			+ " inner join PerfilAcessoUsuario pau on pau.grupoAcesso = ga "
			+ " inner join Usuarios u on u.id = pau.idUsuario              "
			+ "   where u.id  = ?1                                         "
			+ "     and m.visivel = true                                   "
			+ "  order by m.descricao                                      ")
	public Optional<List<Modulo>> getComAcesso(Long idUsuario);
	
}
