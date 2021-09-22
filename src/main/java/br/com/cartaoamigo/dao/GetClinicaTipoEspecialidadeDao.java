package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.ClinicaTipoEspecialidadeDTO;

@Component
public class GetClinicaTipoEspecialidadeDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<ClinicaTipoEspecialidadeDTO> getAllFilterClinicaTipoEspecialidade(String cidade,
												                                  String uf,
												                                  Long idTipoEspecialidade) {
		StringBuilder sql = new StringBuilder();
	
		sql.append(" SELECT distinct                                                                              ");
		sql.append("        c.id_clinica,                                                                         ");
		sql.append("        c.nm_razao_social,                                                                    ");
		sql.append("        c.ds_email,                                                                           ");
		sql.append("        c.nr_cnpj,                                                                            ");
		sql.append("        c.nm_fantasia,                                                                        ");
		sql.append("        c.st_ativo,                                                                           ");
		sql.append("        c.dt_cadastro,                                                                        ");
		sql.append("        c.uf_endereco,                                                                        ");
		sql.append("        c.nm_cidade,                                                                          ");
		sql.append("        c.ds_bairro,                                                                          ");
		sql.append("        c.ds_endereco,                                                                        ");
		sql.append("        c.nr_telefone01,                                                                      ");
		sql.append("        c.nr_telefone02,                                                                      ");
		sql.append("        c.nr_fone_celular,                                                                    ");
		sql.append("        te.descricao                                                                          ");
		sql.append(" FROM clinicas c                                                                              ");
		sql.append(" inner join clinicas_tipo_especialidades cte on cte.id_clinica = c.id_clinica                 ");
		sql.append(" inner join tipo_especialidades te on te.id_tipo_especialidade = cte.id_tipo_especialidade    ");
		sql.append(" WHERE 1=1                                                                                    ");
		sql.append(" and c.st_ativo = 'S'                                                                         ");
		sql.append(" and cte.st_ativo = 'S'                                                                       ");
		
		if(Objects.nonNull(idTipoEspecialidade)) {
			sql.append("  and :p_id_tipo_especialidade = cte.id_tipo_especialidade");
		}

		if(StringUtils.isNotEmpty(uf)) {
			sql.append("  and :p_uf = c.uf_endereco   ");
		}
		
		if(StringUtils.isNotEmpty(cidade)) {
			sql.append("  and upper(:p_cidade) = upper(c.ds_bairro)   ");
		}
		
		sql.append(" order by 2 ");
		
		Query query = em.createNativeQuery(sql.toString());
			
		if(Objects.nonNull(idTipoEspecialidade)) {
			query.setParameter("p_id_tipo_especialidade", idTipoEspecialidade);
		}
		
		if(StringUtils.isNotEmpty(uf) ) {
			query.setParameter("p_uf", uf);
		}
		
		if(StringUtils.isNotEmpty(cidade) ) {
			query.setParameter("p_cidade", cidade);
		}
		
		List<Object[]> values = query.getResultList();
		
		List<ClinicaTipoEspecialidadeDTO> retorno = new ArrayList<ClinicaTipoEspecialidadeDTO>();
		values.stream().forEach( colunas -> retorno.add(new ClinicaTipoEspecialidadeDTO(colunas)));
		
		return retorno;
	}
	
}
