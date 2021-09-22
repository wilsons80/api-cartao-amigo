package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.CorretorComboDTO;
import br.com.cartaoamigo.dao.dto.CorretorDTO;
import br.com.cartaoamigo.infra.util.Java8DateUtil;

@Component
public class GetCorretorDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public List<CorretorComboDTO> getAllCombo() {
		StringBuilder sql = new StringBuilder();

		sql.append(" select c.id_corretor,                                                             ");
		sql.append("        pf.nm_pessoa_fisica,                                                       ");
		sql.append("        c.nr_codigo                                                                ");
		sql.append("   from corretor c                                                                 ");
		sql.append("  inner join pessoas_fisicas pf on pf.id_pessoa_fisica = c.id_pessoa_fisica        ");
		sql.append("  where 1=1                                                                        ");
		sql.append("   order by 2                                                                      ");

		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<CorretorComboDTO> retorno = new ArrayList<CorretorComboDTO>();
		values.stream().forEach( colunas -> retorno.add(new CorretorComboDTO(colunas)));
		
		return retorno;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CorretorDTO> getAllFilter(Long idCorretor, 
	                                      String ativo, 
	                                      LocalDate dataInicioCadastro, 
	                                      LocalDate dataFimCadastro) {
		StringBuilder sql = new StringBuilder();
	
		
		sql.append("select c.id_corretor,                                                           ");
		sql.append("       pf.nm_pessoa_fisica,                                                     ");
		sql.append("       pf.ds_email,                                                             ");
		sql.append("       pf.nr_cpf,                                                               ");
		sql.append("       c.st_ativo,                                                              ");
		sql.append("       c.dt_cadastro,                                                           ");
		sql.append("       c.public_key,                                                            ");
		sql.append("       c.link_pagamento,                                                        ");
		sql.append("       c.token                                                                  ");
		sql.append("  from corretor c                                                               ");
		sql.append(" inner join pessoas_fisicas pf on pf.id_pessoa_fisica = c.id_pessoa_fisica      ");
		sql.append(" where 1=1                                                                      ");
		
		if(Objects.nonNull(idCorretor)) {
			sql.append("  and :p_id_corretor = c.id_corretor  ");
		}

		if(StringUtils.isNotEmpty(ativo)) {
			sql.append("  and :p_ativo = c.st_ativo  ");
		}
		
		if(Objects.nonNull(dataInicioCadastro)) {
			sql.append("   AND DATE_TRUNC('DAY', c.dt_cadastro) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_cadastro ,'dd/mm/yyyy') )  ");	
		}
		
		if(Objects.nonNull(dataFimCadastro)) {
			sql.append("  and DATE_TRUNC('DAY', c.dt_cadastro) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_cadastro ,'dd/mm/yyyy') )     ");
		}
		
		sql.append(" order by 2 ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		
		if(Objects.nonNull(idCorretor)) {
			query.setParameter("p_id_corretor", idCorretor);
		}
	
		if(StringUtils.isNotEmpty(ativo) ) {
			query.setParameter("p_ativo", ativo.toUpperCase());
		}

		if(Objects.nonNull(dataInicioCadastro)) {
			query.setParameter("p_dt_inicio_cadastro", Java8DateUtil.getLocalDateFormater(dataInicioCadastro));
		}
		
		if(Objects.nonNull(dataFimCadastro)) {
			query.setParameter("p_dt_fim_cadastro", Java8DateUtil.getLocalDateFormater(dataFimCadastro));
		}
		
		List<Object[]> values = query.getResultList();
		
		List<CorretorDTO> retorno = new ArrayList<CorretorDTO>();
		values.stream().forEach( colunas -> retorno.add(new CorretorDTO(colunas)));
		
		return retorno;
	}
	
}
