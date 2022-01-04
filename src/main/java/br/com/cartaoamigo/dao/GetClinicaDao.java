package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.ClinicaComboDTO;
import br.com.cartaoamigo.dao.dto.ClinicaDTO;
import br.com.cartaoamigo.infra.util.Java8DateUtil;
import br.com.cartaoamigo.infra.util.StringUtil;

@Component
public class GetClinicaDao extends BaseDao{

	

	@SuppressWarnings("unchecked")
	public List<ClinicaComboDTO> getAllCombo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT c.id_clinica,                                                              ");
		sql.append("        c.nm_razao_social                                                          ");
		sql.append(" FROM clinicas c                                                                   ");
		sql.append(" WHERE 1 = 1                                                                       ");
		sql.append(" order by 2                                                                        ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<ClinicaComboDTO> retorno = new ArrayList<ClinicaComboDTO>();
		values.stream().forEach( colunas -> retorno.add(new ClinicaComboDTO(colunas)));
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<ClinicaDTO> getAllFilterClinica(Long idClinica, 
			                                    String ativo, 
			                                    LocalDate dataInicioGerado, 
			                                    LocalDate dataFimGerado, 
			                                    String bairro, 
			                                    Long idTipoEspecialidae) {
		StringBuilder sql = new StringBuilder();
	
		sql.append(" SELECT distinct                                                                ");
		sql.append("        c.id_clinica,                                                           ");
		sql.append("        c.nm_razao_social,                                                      ");
		sql.append("        c.ds_email,                                                             ");
		sql.append("        c.nr_cnpj,                                                              ");
		sql.append("        c.nm_fantasia,                                                          ");
		sql.append("        c.st_ativo,                                                             ");
		sql.append("        c.dt_cadastro,                                                          ");
		sql.append("        c.uf_endereco,                                                          ");
		sql.append("        c.nm_cidade,                                                            ");
		sql.append("        c.ds_bairro,                                                             ");
		sql.append("        c.ds_endereco,                                                          ");
		sql.append("        c.nr_telefone01,                                                        ");
		sql.append("        c.nr_telefone02,                                                        ");
		sql.append("        c.nr_fone_celular                                                       ");
		sql.append(" FROM clinicas c                                                                ");
		sql.append(" left join clinicas_tipo_especialidades cte on cte.id_clinica = c.id_clinica   ");
		sql.append(" WHERE 1=1                                                                      ");
		
	      
		if(StringUtils.isNotEmpty(bairro)) {
			sql.append("  and upper(c.ds_bairro) like  '%"+StringUtil.removerCaractereEspecial(bairro.trim()).toUpperCase()+"%' ");
		}	
	
		if(Objects.nonNull(idClinica)) {
			sql.append("  and :p_id_clinica = c.id_clinica  ");
		}
		
		if(Objects.nonNull(idTipoEspecialidae)) {
			sql.append("  and :p_id_tipo_especialidade = cte.id_tipo_especialidade");
		}

		if(StringUtils.isNotEmpty(ativo)) {
			sql.append("  and :p_ativo = c.st_ativo  ");
		}
		
		if(Objects.nonNull(dataInicioGerado)) {
			sql.append("   AND DATE_TRUNC('DAY', c.dt_cadastro) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_gerado ,'dd/mm/yyyy') )  ");	
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			sql.append("  and DATE_TRUNC('DAY', c.dt_cadastro) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_gerado ,'dd/mm/yyyy') )     ");
		}
		
		sql.append(" order by 2");
		
		Query query = em.createNativeQuery(sql.toString());
		
		
		if(Objects.nonNull(idClinica)) {
			query.setParameter("p_id_clinica", idClinica);
		}
		
		if(Objects.nonNull(idTipoEspecialidae)) {
			query.setParameter("p_id_tipo_especialidade", idTipoEspecialidae);
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
		
		List<ClinicaDTO> retorno = new ArrayList<ClinicaDTO>();
		values.stream().forEach( colunas -> retorno.add(new ClinicaDTO(colunas)));
		
		return retorno;
	}
	
}
