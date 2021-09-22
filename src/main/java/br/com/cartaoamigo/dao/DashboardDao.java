package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.dashboard.AvaliacoesDTO;

@Component
public class DashboardDao extends BaseDao {
	
	public AvaliacoesDTO getUltimaAvaliacaoDoMes(Long idUnidade, Long idUsuario, LocalDate mesData ) {
		StringBuilder sql = new StringBuilder();
	
			
	    sql.append(" select uni.nm_unidade, uni.cd_unidade, round(avg(ava.vl_nota_final),2)   ");
	    sql.append("   from unidades uni                                                      "); 
	    sql.append("    inner join usuarios_unidades uu on uni.id_unidade = uu.id_unidade     "); 
	    sql.append("    inner join usuarios use on use.id_usuario = uu.id_usuario             "); 
	    sql.append("    inner join avaliacao ava on  ava.id_unidade = uu.id_unidade           "); 
	    sql.append(" where 1 = 1                                                              "); 
	    sql.append("   and use.id_usuario = :idUsuario                                        ");   
	    sql.append("   and ava.id_unidade = :idUnidade                                        ");   
	    sql.append("   and TO_CHAR(ava.dt_final, 'MM') = :p_mesData                           "); 
	    sql.append("   and ava.finalizada = 'S'                                               ");
	    sql.append("  group by uni.nm_unidade,uni.cd_unidade                                  ");
       
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("idUnidade", idUnidade);
		query.setParameter("p_mesData", mesData.format(DateTimeFormatter.ofPattern("MM")));
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<AvaliacoesDTO> retorno = new ArrayList<AvaliacoesDTO>();
		values.stream().forEach( colunas -> retorno.add(new AvaliacoesDTO(colunas)));
		
		return retorno.isEmpty() ? null : retorno.get(0);
		
	}
	
	

}
