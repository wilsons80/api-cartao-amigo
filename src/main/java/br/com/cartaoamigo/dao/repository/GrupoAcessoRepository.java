package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.GrupoAcesso;

@Repository
public interface GrupoAcessoRepository extends JpaRepository<GrupoAcesso, Long>{
	
	@Query("select ga from GrupoAcesso ga                                                          "
			+ " where 1=1                                                                          "
			+ " and ga.nomeGrupoAcesso not in('GP_ASSOCIADO_DEPENDENTE', 'GP_ASSOCIADO_TITULAR', 'ROOT_ADMINISTRATIVO', 'ROOT_CONTROLE_ACESSO' )                           "
			+ " and ga.visivel = true                                                              "
			+ " order by ga.nomeGrupoAcesso asc                                                    ")
	Optional<List<GrupoAcesso>> findAllGruposParaUsuariosAdministrativos();
	
	@Query("select ga from GrupoAcesso ga                                                          "
			+ " where 1=1                                                                          "
			+ " and ga.nomeGrupoAcesso not in('GP_ASSOCIADO_DEPENDENTE')                           "
			+ " order by ga.nomeGrupoAcesso asc                                                    ")
	Optional<List<GrupoAcesso>> findAllGruposParaUsuariosRoot();

	
	@Query("select ga from GrupoAcesso ga                                                           "
			+ " where upper(ga.nomeGrupoAcesso) = upper(?1)                                         "
			+ "   and ga.visivel = true                                                             ")
	Optional<GrupoAcesso> findByNome(String nome);
	
	
	@Query("select ga from GrupoAcesso ga "
			+ " where ga.nomeGrupoAcesso = 'GP_ASSOCIADO_TITULAR'")
	Optional<GrupoAcesso> findGruposAssociadoTitular();
	
	
	@Query("select ga from GrupoAcesso ga "
			+ " where ga.nomeGrupoAcesso = 'GP_ASSOCIADO_DEPENDENTE'")
	Optional<GrupoAcesso> findGruposAssociadoDependente();
	
	@Query(value = "SELECT distinct ga from GrupoAcesso ga                                        "
			+ " inner join GrupoAcessoModulos gam on ga.id = gam.idGrupoAcesso                    "
			+ " inner join Modulo m on m = gam.modulo										      "
			+ "     where 1=1                                                                     "
			+ "     and ga.visivel = true                                                         "
			+ "     and (?1 is null or m.id = ?1)                                                 "
			+ "     and (?2 is null or upper(ga.nomeGrupoAcesso) like upper('%?2%') )             "
			+ "     and (?3 is true and ga.nomeGrupoAcesso not in('GP_ASSOCIADO_DEPENDENTE')      "
			+ "          or                                                                       "
			+ "          ?3 is false and ga.nomeGrupoAcesso not in('GP_ASSOCIADO_DEPENDENTE', 'GP_ASSOCIADO_TITULAR', 'ROOT_ADMINISTRATIVO', 'ROOT_CONTROLE_ACESSO' )  ) "
			+ " order by ga.nomeGrupoAcesso asc                                                   ")
	public Optional<List<GrupoAcesso>> findByFilter(Long idModulo, String nome, boolean isUsuarioRoot);	
}
