package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.CidadeDTO;

@Component
public class GetEnderecoDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<CidadeDTO> getAllBairrosPorUF(String uf) {
		StringBuilder sql = new StringBuilder();

		
		sql.append(" select distinct c.id_clinica, upper(c.ds_bairro)                                    ");
		sql.append("  from clinicas c                                                                    ");
		sql.append(" where 1=1                                                                           ");
		
		if(StringUtils.isNotEmpty(uf) ) {
			sql.append("  and upper(:p_uf) = upper(c.uf_endereco)  ");
		}
				
		sql.append(" order by 2 ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(StringUtils.isNotEmpty(uf) ) {
			query.setParameter("p_uf", uf.toUpperCase());
		}

		List<Object[]> values = query.getResultList();
		
		List<CidadeDTO> retorno = new ArrayList<CidadeDTO>();
		values.stream().forEach( colunas -> retorno.add(new CidadeDTO(colunas)));
		
		return retorno;
	}
			
}



