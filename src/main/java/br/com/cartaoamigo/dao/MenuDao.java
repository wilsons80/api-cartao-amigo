package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.MenuDTO;

@Component
public class MenuDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<MenuDTO> getMenuPrincipal(Long idUsuario) {
		StringBuilder sql = new StringBuilder();
		
		/*
		sql.append(" with recursive home (id_modulo_pai, modulo_pai, id_modulo_filho, modulo_filho, nivel) as(    ");
		sql.append(" 	  	select m.id_modulo, m.nm_modulo, m.id_modulo, m.nm_modulo, 'PAI'                      ");
		sql.append(" 	      from usuarios_grupos ug,                                                            ");
		sql.append(" 	           usuarios_sistema us,                                                           ");
		sql.append(" 	           modulos m,                                                                     ");
		sql.append(" 	           grupos_modulos gm                                                              ");
		sql.append(" 	     where ug.id_grupo_modulo  = gm.id_grupo_modulo                                       ");
		sql.append(" 	       and gm.id_modulo        = m.id_modulo                                              ");
		sql.append(" 	       and ug.id_usuario       = us.id_usuario                                            ");
		sql.append(" 	       and us.st_ativo         = 'S'                                                      ");
		sql.append(" 	       and gm.id_unidade       = :idUnidade                                               ");
		sql.append(" 	       and us.id_usuario       = :idUsuario                                               ");
		sql.append(" 	  	   and m.modulo_pai        is null                                                    ");
		sql.append(" 	  	union all                                                                             ");
        sql.append("        	select filho.id_modulo, filho.nm_modulo, pai.id_modulo, pai.nm_modulo, 'FILHO'    ");
        sql.append("        	  from modulos as pai,                                                            ");
        sql.append("        	       modulos as filho,                                                          ");
        sql.append("                 usuarios_grupos ug,                                                          ");
        sql.append("                 usuarios_sistema us,                                                         ");
        sql.append("                 grupos_modulos gm   	                                                      ");
        sql.append("          where pai.modulo_pai = filho.id_modulo                                              ");
        sql.append("            and ug.id_grupo_modulo  = gm.id_grupo_modulo                                      ");
        sql.append("            and gm.id_modulo        = filho.id_modulo                                         ");
        sql.append("            and ug.id_usuario       = us.id_usuario                                           ");
        sql.append("            and us.st_ativo         = 'S'                                                     ");
        sql.append("            and gm.id_unidade       = :idUnidade                                              ");
        sql.append("            and us.id_usuario       = :idUsuario                                              ");
	    sql.append("            and pai.modulo_pai    is not null    		                                      ");
		sql.append(" 	  )                                                                                       ");
		sql.append(" 	 select * from home                                                                       ");
		sql.append(" 	  order by 2,5 desc                                                                       ");
		*/
		
		
		sql.append(" select DISTINCT m.id_modulo,                                                            ");
		sql.append("        m.nome                                                                           ");
		sql.append("   from modulos m                                                                        ");
		sql.append("   inner join grupo_acesso_modulos gam on gam.id_modulo = m.id_modulo                    ");
		sql.append("   inner join grupo_acesso ga on ga.id_grupo_acesso = gam.id_grupo_acesso                ");
		sql.append("   inner join perfil_acesso_usuario pau on pau.id_grupo_acesso = ga.id_grupo_acesso      ");
		sql.append("   inner join usuarios u on u.id_usuario = pau.id_usuario                                ");
		sql.append("   inner join tipo_acesso_usuarios tau on tau.id_usuario = u.id_usuario                  ");
		sql.append("   inner join tipo_usuario tu on tu.id_tipo_usuario = tau.id_tipo_usuario                ");
		sql.append(" where 1=1                                                                               ");
		sql.append("   and u.st_ativo        = 'S'                                                           ");
		sql.append("   and u.id_usuario = :idUsuario                                                         ");
		sql.append(" order by m.nome                                                                         ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUsuario", idUsuario);
		
		List<Object[]> values = query.getResultList();
		
		List<MenuDTO> retorno = new ArrayList<MenuDTO>();
		values.stream().forEach( colunas -> retorno.add(new MenuDTO(colunas)));
		
		return retorno;
	}
	

}
