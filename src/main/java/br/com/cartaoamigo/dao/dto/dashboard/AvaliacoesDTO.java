package br.com.cartaoamigo.dao.dto.dashboard;

import java.math.BigDecimal;

public class AvaliacoesDTO {

	private String codigoUnidade;
	private String nomeUnidade;
	private Double notaUnidade;
	
	public AvaliacoesDTO() {
	}

	public AvaliacoesDTO(Object[] colunas) {
		this.nomeUnidade   = (String) colunas[0];
		this.codigoUnidade = (String) colunas[1];
		this.notaUnidade   = (colunas[2] != null)? ((BigDecimal)colunas[2]).doubleValue() : null;
	}

	public String getCodigoUnidade() {
		return codigoUnidade;
	}

	public void setCodigoUnidade(String codigoUnidade) {
		this.codigoUnidade = codigoUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public Double getNotaUnidade() {
		return notaUnidade;
	}

	public void setNotaUnidade(Double notaUnidade) {
		this.notaUnidade = notaUnidade;
	}

}
