package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.ImpressaoCartaoDTO;
import br.com.cartaoamigo.infra.util.Java8DateUtil;

@Component
public class ImpressaoCartaoDao extends BaseDao{
	
	
	public Optional<List<ImpressaoCartaoDTO>> getAllFilter(Long idPessoaFisica, String numeroCartao,  LocalDate dataInicioGerado, LocalDate dataFimGerado,
			                                               LocalDate dataInicioImpresso, LocalDate dataFimImpresso, String impresso, String tipoAssociado){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select c.id_cartao,                                                                                                                                                   ");
		sql.append("        pf.nm_pessoa_fisica,                                                                                                                                           ");
		sql.append("        c.nr_cartao,                                                                                                                                                   ");
		sql.append("        c.st_ativo,                                                                                                                                                    ");
		sql.append("        c.st_titular,                                                                                                                                                  ");
		sql.append("        c.dt_impressao,                                                                                                                                                ");
		sql.append("        c.url_qrcode,                                                                                                                                                  ");
		sql.append("        c.dt_criado,                                                                                                                                                   ");
		sql.append("        c.id_titular,                                                                                                                                                  ");
		sql.append("        c.dt_validade_plano                                                                                                                                           ");
		sql.append("   from cartao c                                                                                                                                                       ");
		sql.append("  inner join pessoas_fisicas pf on pf.id_pessoa_fisica = c.id_pessoa_fisica                                                                                            ");
		sql.append(" WHERE 1 = 1                                                                                                                                                           ");
		
		if(Objects.nonNull(idPessoaFisica)) {
			sql.append("  and :p_pessoa_fisica = pf.id_pessoa_fisica  ");
		}

		if(StringUtils.isNotEmpty(numeroCartao)) {
			sql.append("  and :p_numero_cartao = c.nr_cartao  ");
		}
		
		if(StringUtils.isNotEmpty(impresso) && impresso.equals("S")) {
			sql.append("  and c.dt_impressao is not null ");
		}
		if(StringUtils.isNotEmpty(impresso) && impresso.equals("N")) {
			sql.append("  and c.dt_impressao is null ");
		}

		if(StringUtils.isNotEmpty(tipoAssociado) && tipoAssociado.equals("T")) {
			sql.append("  and c.st_titular = 'S' ");
		}
		if(StringUtils.isNotEmpty(tipoAssociado) && tipoAssociado.equals("D")) {
			sql.append("  and c.st_titular = 'N' ");
		}

		
		if(Objects.nonNull(dataInicioGerado)) {
			sql.append("   AND DATE_TRUNC('DAY', c.dt_criado) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_gerado ,'dd/mm/yyyy') )  ");
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			sql.append("  and DATE_TRUNC('DAY', c.dt_criado) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_gerado ,'dd/mm/yyyy') )     ");
		}

		if(Objects.nonNull(dataInicioImpresso)) {
			sql.append("   AND DATE_TRUNC('DAY', c.dt_impressao) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_impresso ,'dd/mm/yyyy') )  ");
		}
		
		if(Objects.nonNull(dataFimImpresso)) {
			sql.append("  and DATE_TRUNC('DAY', c.dt_impressao) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_impresso ,'dd/mm/yyyy') )     ");
		}

		
		sql.append(" order by c.dt_criado ");
		
		
		Query query = em.createNativeQuery(sql.toString());

		if(Objects.nonNull(idPessoaFisica)) {
			query.setParameter("p_pessoa_fisica", idPessoaFisica);
		}

		if(StringUtils.isNotEmpty(numeroCartao)) {
			query.setParameter("p_numero_cartao", numeroCartao);
		}

		if(Objects.nonNull(dataInicioGerado)) {
			query.setParameter("p_dt_inicio_gerado", Java8DateUtil.getLocalDateFormater(dataInicioGerado));
		}
		
		if(Objects.nonNull(dataFimGerado)) {
			query.setParameter("p_dt_fim_gerado", Java8DateUtil.getLocalDateFormater(dataFimGerado));
		}

		if(Objects.nonNull(dataInicioImpresso)) {
			query.setParameter("p_dt_inicio_impresso", Java8DateUtil.getLocalDateFormater(dataInicioImpresso));
		}
		
		if(Objects.nonNull(dataFimImpresso)) {
			query.setParameter("p_dt_fim_impresso", Java8DateUtil.getLocalDateFormater(dataFimImpresso));
		}

		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<ImpressaoCartaoDTO> retorno = new ArrayList<ImpressaoCartaoDTO>();
		values.stream().forEach( colunas -> retorno.add(new ImpressaoCartaoDTO(colunas)));
		
		return Optional.ofNullable(retorno);		
	}
	
	

	
}
