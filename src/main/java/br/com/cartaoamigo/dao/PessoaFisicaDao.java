package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.AssociadoComboDTO;

@Component
public class PessoaFisicaDao extends BaseDao{
	
	
	public List<AssociadoComboDTO> getAllByAssociadosCombo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select pf.id_pessoa_fisica,                                               ");
		sql.append("        pf.nm_pessoa_fisica,                                               ");
		sql.append("        pf.nr_cpf                                                          ");		
		sql.append("  from pessoas_fisicas pf                                                  ");
		sql.append("  inner join usuarios u on u.id_pessoa_fisica = pf.id_pessoa_fisica        ");
		sql.append("  inner join tipo_acesso_usuarios tau on tau.id_usuario = u.id_usuario     ");
		sql.append("  inner join tipo_usuario tu on tu.id_tipo_usuario = tau.id_tipo_usuario   ");
		sql.append(" where 1=1                                                                 ");
		sql.append("   and tu.id_tipo_usuario in (1,2)                                         ");
		sql.append("  order by pf.nm_pessoa_fisica                                             ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<AssociadoComboDTO> retorno = new ArrayList<AssociadoComboDTO>();
		values.stream().forEach( colunas -> retorno.add(new AssociadoComboDTO(colunas)));
		
		return retorno;
		
	}
	
}
