package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class UnidadeModuloAcessoDTO {
	
	private Long   idUnidade;
	private String nomeUnidade;
	
	public UnidadeModuloAcessoDTO() {
	}
	
	public UnidadeModuloAcessoDTO(Object[] colunas) {
		this.idUnidade      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;;
		this.nomeUnidade    = (String) colunas[1];
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

}
