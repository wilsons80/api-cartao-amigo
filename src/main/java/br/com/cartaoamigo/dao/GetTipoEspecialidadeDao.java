package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.TipoEspecialidadeComboDTO;

@Component
public class GetTipoEspecialidadeDao extends BaseDao{


	@SuppressWarnings("unchecked")
	public List<TipoEspecialidadeComboDTO> getAllCombo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT c.id_tipo_especialidade,                                             ");
		sql.append("        upper(c.descricao)                                                    ");
		sql.append(" FROM tipo_especialidades c                                                  ");
		sql.append(" WHERE 1 = 1                                                                 ");
		sql.append(" order by 2                                                                  ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<TipoEspecialidadeComboDTO> retorno = new ArrayList<TipoEspecialidadeComboDTO>();
		values.stream().forEach( colunas -> retorno.add(new TipoEspecialidadeComboDTO(colunas)));
		
		return retorno;
	}
	
	
}
