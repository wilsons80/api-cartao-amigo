package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class AcessoDTO {

	private Long idModulo;
	private String nomeModulo;
	private Boolean altera;
	private Boolean consulta;
	private Boolean deleta;
	private Boolean insere;

	public AcessoDTO() {
	}

	public AcessoDTO(Object[] colunas) {
		this.idModulo   = (colunas[0] != null) ? ((BigDecimal) colunas[0]).longValue() : null;
		this.nomeModulo = (String) colunas[1];
		this.consulta   = StringUtils.isEmpty((String) colunas[2]) || colunas[2].equals("N")  ? false : true;
		this.altera     = StringUtils.isEmpty((String) colunas[3]) || colunas[3].equals("N")  ? false : true;
		this.deleta     = StringUtils.isEmpty((String) colunas[4]) || colunas[4].equals("N")  ? false : true;
		this.insere     = StringUtils.isEmpty((String) colunas[5]) || colunas[5].equals("N")  ? false : true;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNomeModulo() {
		return nomeModulo;
	}

	public void setNomeModulo(String nomeModulo) {
		this.nomeModulo = nomeModulo;
	}

	public Boolean getAltera() {
		return altera;
	}

	public void setAltera(Boolean altera) {
		this.altera = altera;
	}

	public Boolean getConsulta() {
		return consulta;
	}

	public void setConsulta(Boolean consulta) {
		this.consulta = consulta;
	}

	public Boolean getDeleta() {
		return deleta;
	}

	public void setDeleta(Boolean deleta) {
		this.deleta = deleta;
	}

	public Boolean getInsere() {
		return insere;
	}

	public void setInsere(Boolean insere) {
		this.insere = insere;
	}

}
