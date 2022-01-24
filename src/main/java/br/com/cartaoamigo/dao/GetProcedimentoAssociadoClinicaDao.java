package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.ProcedimentoAssociadoClinicaDTO;

@Component
public class GetProcedimentoAssociadoClinicaDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<ProcedimentoAssociadoClinicaDTO> getAllConsultasRealizadas(Long idTitular) {
		StringBuilder sql = new StringBuilder();
	
		sql.append("select cartao.nome_impresso,                                                                                         ");
		sql.append("       clinica.nm_fantasia,                                                                                          ");
		sql.append("       te.descricao,                                                                                                 ");
		sql.append("       pac.dt_procedimento,                                                                                          ");
		sql.append("       pac.assinatura_ativa                                                                                          ");
		sql.append("  from procedimento_associado_clinica pac                                                                            ");
		sql.append("  inner join clinicas_tipo_especialidades cte on cte.id_clinica_tipo_especidades = pac.id_clinica_tipo_especidades   ");
		sql.append("  inner join tipo_especialidades te on te.id_tipo_especialidade = cte.id_tipo_especialidade                          ");
		sql.append("  inner join clinicas clinica on clinica.id_clinica = cte.id_clinica                                                 ");
		sql.append("  inner join cartao cartao on cartao.id_cartao = pac.id_cartao                                                       ");
		sql.append("  left join titular titular on titular.id_titular = cartao.id_titular                                                ");
		sql.append("  left join dependentes_titular dep on dep.id_titular = cartao.id_titular                                            ");
		sql.append(" where 1=1                                                                                                           ");
		sql.append("   and cartao.id_titular = :id_titular                                                                               ");
		sql.append(" order by pac.dt_procedimento desc                                                                                   ");
		 
		
		Query query = em.createNativeQuery(sql.toString());
			
		if(Objects.nonNull(idTitular)) {
			query.setParameter("id_titular", idTitular);
		}
		
		List<Object[]> values = query.getResultList();
		
		List<ProcedimentoAssociadoClinicaDTO> retorno = new ArrayList<ProcedimentoAssociadoClinicaDTO>();
		values.stream().forEach( colunas -> retorno.add(new ProcedimentoAssociadoClinicaDTO(colunas)));
		
		return retorno;
	}
	
}
