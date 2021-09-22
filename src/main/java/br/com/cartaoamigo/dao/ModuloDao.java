package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.ModuloAcessoDTO;
import br.com.cartaoamigo.dao.dto.ModuloDTO;
import br.com.cartaoamigo.dao.dto.ModuloFilhoDTO;

@Component
public class ModuloDao extends BaseDao{
	

	@SuppressWarnings("unchecked")
	public List<ModuloDTO> getAllModulo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("   select m.id_modulo,                                           ");
		sql.append("          m.descricao,                                           ");
		sql.append("          m.nome,                                                ");
		sql.append("          m.agrupador,                                           ");
		sql.append("          m.modulo_pai,                                          ");
		sql.append("          m.routerlink,                                          ");
		sql.append("          m.href,                                                ");
		sql.append("          m.icon,                                                ");
		sql.append("          m.target,                                              ");
		sql.append("          m.visivel                                              ");
		sql.append("     from modulos m                                              ");
		sql.append("    where 1 = 1                                                  ");
		sql.append("      and m.visivel = 'S'                                        ");
		sql.append("   order by m.descricao                                          ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<ModuloDTO> retorno = new ArrayList<ModuloDTO>();
		values.stream().forEach( colunas -> retorno.add(new ModuloDTO(colunas)));
		
		return retorno;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ModuloFilhoDTO> getAllModuloFilhos(List<Long> idsModulosPai) {
		StringBuilder sql = new StringBuilder();
		
  	    sql.append(" with recursive home (id_modulo_pai) as(                                                                                                                             ");    
		sql.append(" 	  	   select m.id_modulo,m.id_modulo,m.descricao,m.nome,m.agrupador,m.modulo_pai,m.routerlink,m.href,m.icon,m.target,m.visivel                                  ");
		sql.append("           from modulos m                                                                                                                                            ");
		sql.append("          where 1 = 1                                                                                                                                                ");
		sql.append("            and m.agrupador    = 'S'                                                                                                                                 ");
		sql.append("            and m.visivel      = 'S'                                                                                                                                 ");
		sql.append(" 	  	union all                                                                                                                                                    ");
		sql.append("    	   select filho.id_modulo, pai.id_modulo,pai.descricao,pai.nome,pai.agrupador,pai.modulo_pai,pai.routerlink,pai.href,pai.icon,pai.target,pai.visivel       ");                                                                       
		sql.append("           from modulos as pai,                                                                                                                                      ");
		sql.append("        	      modulos as filho                                                                                                                                   ");
		sql.append("          where 1 = 1                                                                                                                                                ");
		sql.append("            and pai.modulo_pai = filho.id_modulo                                                                                                                     ");
		sql.append("            and pai.agrupador    = 'N'                                                                                                                               ");
		sql.append("            and pai.visivel      = 'S'                                                                                                                               ");
		sql.append(" 	  )                                                                                                                                                              ");
		sql.append(" 	 select * from home                                                                                                                                              ");
		sql.append("     where id_modulo_pai in(                                                                                                                                         ");
		sql.append(String.join(", ", idsModulosPai.stream().map(String::valueOf).collect(Collectors.toList())));
		sql.append(" )");
		sql.append("       and agrupador = 'N'                                                                                                                                           ");
		sql.append("       order by 3                                                                                                                                           ");
	                          
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<ModuloFilhoDTO> retorno = new ArrayList<ModuloFilhoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ModuloFilhoDTO(colunas)));
		
		return retorno;
	}	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ModuloDTO> getAllModuloFilhos() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("   select distinct m.id_modulo,                                                                    ");
		sql.append("          m.descricao,                                                                    ");
		sql.append("          m.nome,                                                                         ");
		sql.append("          m.agrupador,                                                                    ");
		sql.append("          m.modulo_pai,                                                                   ");
		sql.append("          m.routerlink,                                                                   ");
		sql.append("          m.href,                                                                         ");
		sql.append("          m.icon,                                                                         ");
		sql.append("          m.target,                                                                       ");
		sql.append("          m.visivel                                                                       ");
		sql.append("     from modulos m                                                                       ");
		sql.append("   inner join grupo_acesso_modulos gam on gam.id_modulo = m.id_modulo                     ");
		sql.append("   inner join grupo_acesso ga on ga.id_grupo_acesso = gam.id_grupo_acesso                 ");
		sql.append("   inner join perfil_acesso_usuario pau on pau.id_grupo_acesso = ga.id_grupo_acesso       ");
		sql.append("   inner join usuarios u on u.id_usuario = pau.id_usuario                                 ");
		sql.append("   inner join tipo_acesso_usuarios tau on tau.id_usuario = u.id_usuario                   ");
		sql.append("    where 1 = 1                                                                           ");
		sql.append("      and m.agrupador = 'N'                                                               ");
		sql.append("      and m.visivel   = 'S'                                                               ");
		sql.append("   order by m.descricao                                                                   ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<ModuloDTO> retorno = new ArrayList<ModuloDTO>();
		values.stream().forEach( colunas -> retorno.add(new ModuloDTO(colunas)));
		
		return retorno;
	}	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ModuloAcessoDTO> getAllModuloComAcesso(Long idUsuario, Long idTipoAcessoUsuario) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select distinct                                                                          ");
		sql.append("        m.id_modulo,                                                                      ");
		sql.append("        m.nome                                                                            ");
		sql.append("   from modulos m                                                                         ");
		sql.append("   inner join grupo_acesso_modulos gam on gam.id_modulo = m.id_modulo                     ");
		sql.append("   inner join grupo_acesso ga on ga.id_grupo_acesso = gam.id_grupo_acesso                 ");
		sql.append("   inner join perfil_acesso_usuario pau on pau.id_grupo_acesso = ga.id_grupo_acesso       ");
		sql.append("   inner join usuarios u on u.id_usuario = pau.id_usuario                                 ");
		sql.append("   inner join tipo_acesso_usuarios tau on tau.id_usuario = u.id_usuario                   ");
		sql.append(" where 1=1                                                                                ");
		sql.append("   and u.id_usuario = :idUsuario                                                          ");
		sql.append("   and tau.id_tipo_acesso_usuario = :idTipoAcessoUsuario                                  ");
		sql.append("   and m.visivel = 'S'                                                                    ");
		sql.append("   order by m.nome                                                                        ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUsuario"          , idUsuario);
		query.setParameter("idTipoAcessoUsuario", idTipoAcessoUsuario);
		
		List<Object[]> values = query.getResultList();
		
		List<ModuloAcessoDTO> retorno = new ArrayList<ModuloAcessoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ModuloAcessoDTO(colunas)));
		
		return retorno;
	}	
}
