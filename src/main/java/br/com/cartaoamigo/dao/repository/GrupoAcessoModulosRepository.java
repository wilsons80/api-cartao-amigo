package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.GrupoAcessoModulos;

@Repository
public interface GrupoAcessoModulosRepository extends JpaRepository<GrupoAcessoModulos, Long>{
	
	
	@Query(value = " select gam from GrupoAcessoModulos gam            "
			+ " inner join GrupoAcesso ga on ga.id = gam.idGrupoAcesso "
			+ " where ga.id = ?1                                       ")
	public Optional<List<GrupoAcessoModulos>> findAllByGrupoAcesso(Long idGrupoAcesso);

	@Query(value = " select gam from GrupoAcessoModulos gam            "
			+ " inner join GrupoAcesso ga on ga.id = gam.idGrupoAcesso "
			+ " inner join Modulo m on m = gam.modulo                  "
			+ " where ga.id = ?1                                       "
			+ "   and m.agrupador = false                              ")
	public Optional<List<GrupoAcessoModulos>> findAllFilhosByGrupoAcesso(Long idGrupoAcesso);
	
	@Query(value = " select gam from GrupoAcessoModulos gam            "
			+ " inner join GrupoAcesso ga on ga.id = gam.idGrupoAcesso "
			+ " inner join Modulo modulo on modulo = gam.modulo        "
			+ " inner join Modulo pai on pai = modulo.moduloPai        "
			+ " where 1=1                                              "
			+ "   and ga.id = ?1                                       "
			+ "   and pai.id = ?2                                      "
			+ "   and modulo.id != ?3                                  ")
	public Optional<List<GrupoAcessoModulos>> findOutrosModulosFilhos(Long idGrupoAcesso, Long idModuloPai, Long idModuloFilho);
	
	@Query(value = " select distinct gam from GrupoAcessoModulos gam            "
			+ " inner join GrupoAcesso ga on ga.id = gam.idGrupoAcesso "
			+ " inner join Modulo modulo on modulo = gam.modulo        "
			+ " where 1=1                                              "
			+ "   and ga.id = ?1                                       "
			+ "   and modulo.id = ?2                                  ")
	public Optional<List<GrupoAcessoModulos>> findModuloPai(Long idGrupoAcesso, Long idModuloFilho);

	
}
