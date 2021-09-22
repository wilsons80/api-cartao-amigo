package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.AssociadoComboDTO;
import br.com.cartaoamigo.dao.dto.AssociadosDTO;
import br.com.cartaoamigo.infra.util.Java8DateUtil;

@Component
public class GetAssociadosDao extends BaseDao {

	
	@SuppressWarnings("unchecked")
	public List<AssociadoComboDTO> getAllCombo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT pft.id_pessoa_fisica,                                                      ");
		sql.append("        pft.nm_pessoa_fisica,                                                      ");
		sql.append("        pft.nr_cpf                                                                 ");
		sql.append(" FROM titular t                                                                    ");
		sql.append("   INNER JOIN pessoas_fisicas pft ON pft.id_pessoa_fisica = t.id_pessoa_fisica     ");
		sql.append(" WHERE 1 = 1                                                                       ");
		sql.append(" order by 2                                                                        ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<AssociadoComboDTO> retorno = new ArrayList<AssociadoComboDTO>();
		values.stream().forEach( colunas -> retorno.add(new AssociadoComboDTO(colunas)));
		
		return retorno;
	}

	
	@SuppressWarnings("unchecked")
	public List<AssociadosDTO> getAllFilterAssociados(Long idPessoaFisicaTitular, String ativo, LocalDate dataInicioGerado, LocalDate dataFimGerado) {
		StringBuilder sqlTitular = new StringBuilder();
		
		queryPrincipalTitular(sqlTitular);	
		

		if(Objects.nonNull(idPessoaFisicaTitular)) {
			sqlTitular.append("  and :p_pessoa_fisica_titular = pft.id_pessoa_fisica  ");
		}

		if(StringUtils.isNotEmpty(ativo)) {
			sqlTitular.append("  and :p_ativo = t.st_ativo  ");
		}
		
		if(Objects.nonNull(dataInicioGerado)) {
			sqlTitular.append("   AND DATE_TRUNC('DAY', t.dt_cadastro) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_gerado ,'dd/mm/yyyy') )  ");	
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			sqlTitular.append("  and DATE_TRUNC('DAY', t.dt_cadastro) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_gerado ,'dd/mm/yyyy') )     ");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append(sqlTitular.toString());		
		sql.append(" order by 5 desc ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(Objects.nonNull(idPessoaFisicaTitular)) {
			query.setParameter("p_pessoa_fisica_titular", idPessoaFisicaTitular);
		}

		if(StringUtils.isNotEmpty(ativo) ) {
			query.setParameter("p_ativo", ativo.toUpperCase());
		}

		if(Objects.nonNull(dataInicioGerado)) {
			query.setParameter("p_dt_inicio_gerado", Java8DateUtil.getLocalDateFormater(dataInicioGerado));
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			query.setParameter("p_dt_fim_gerado", Java8DateUtil.getLocalDateFormater(dataFimGerado));
		}

		
		List<Object[]> values = query.getResultList();
		
		List<AssociadosDTO> retorno = new ArrayList<AssociadosDTO>();
		values.stream().forEach( colunas -> retorno.add(new AssociadosDTO(colunas)));
		
		return retorno;
	}

	private void queryPrincipalTitular(StringBuilder sql) {
		sql.append(" select t.id_titular, pft.id_pessoa_fisica, pft.nm_pessoa_fisica, t.st_ativo, t.dt_cadastro, pft.nr_cpf, pft.ds_email         ");       
		sql.append("   from titular t                                                                                                                                                    ");
		sql.append("  inner join pessoas_fisicas pft on pft.id_pessoa_fisica = t.id_pessoa_fisica                                                                                        ");
		sql.append(" where 1=1                                                                                                                                                           ");
	}	
	
			
}
