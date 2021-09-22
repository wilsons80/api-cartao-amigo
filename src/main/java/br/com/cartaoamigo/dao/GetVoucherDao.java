package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.VoucherDTO;
import br.com.cartaoamigo.infra.util.Java8DateUtil;
import br.com.cartaoamigo.infra.util.StringUtil;

@Component
public class GetVoucherDao extends BaseDao{

	
	@SuppressWarnings("unchecked")
	public List<VoucherDTO> getAllFilter(String codigo, 
	                                     String ativo, 
	                                     String utilizado,
	                                     LocalDate dataInicioGerado, 
	                                     LocalDate dataFimGerado) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select  distinct                                                              ");
		sql.append("         v.id_voucher,                                                         ");
		sql.append("         v.cd_voucher,                                                         ");
		sql.append("         v.ds_promocao,                                                        ");
		sql.append("         v.porcentagem_voucher,                                                ");
		sql.append("         v.dt_criacao,                                                         ");
		sql.append("         v.dt_validade,                                                        ");
		sql.append("         v.st_ativo,                                                           ");
		sql.append("         v.st_utilizado,                                                       ");
		sql.append("         v.dt_utilizacao,                                                      ");
		sql.append("         pf.nm_pessoa_fisica                                                   ");
		sql.append("   from voucher v                                                              ");
		sql.append("  left join pessoas_fisicas pf on v.id_pessoa_fisica = pf.id_pessoa_fisica    ");
		sql.append("  where 1=1                                                                    ");

		if(StringUtils.isNotEmpty(codigo)) {
			sql.append("  and upper(v.cd_voucher) like  '%"+StringUtil.removerAcentos(codigo.trim()).toUpperCase()+"%' ");
		}	
	
		if(StringUtils.isNotEmpty(ativo)) {
			sql.append("  and :p_ativo = v.st_ativo  ");
		}
		
		if(StringUtils.isNotEmpty(utilizado)) {
			sql.append("  and :p_utilizado = v.st_utilizado  ");
		}
		
		if(Objects.nonNull(dataInicioGerado)) {
			sql.append("   AND DATE_TRUNC('DAY', v.dt_criacao) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_gerado ,'dd/mm/yyyy') )  ");	
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			sql.append("  and DATE_TRUNC('DAY', v.dt_criacao) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_gerado ,'dd/mm/yyyy') )     ");
		}
		
		sql.append(" order by 5 ");
		
		Query query = em.createNativeQuery(sql.toString());

		if(StringUtils.isNotEmpty(ativo) ) {
			query.setParameter("p_ativo", ativo.toUpperCase());
		}

		if(StringUtils.isNotEmpty(utilizado) ) {
			query.setParameter("p_utilizado", utilizado.toUpperCase());
		}
		
		if(Objects.nonNull(dataInicioGerado)) {
			query.setParameter("p_dt_inicio_gerado", Java8DateUtil.getLocalDateFormater(dataInicioGerado));
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			query.setParameter("p_dt_fim_gerado", Java8DateUtil.getLocalDateFormater(dataFimGerado));
		}

		
		List<Object[]> values = query.getResultList();
		
		List<VoucherDTO> retorno = new ArrayList<VoucherDTO>();
		values.stream().forEach( colunas -> retorno.add(new VoucherDTO(colunas)));
		
		return retorno;
	}
	
}
