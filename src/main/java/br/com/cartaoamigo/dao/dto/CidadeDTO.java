package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class CidadeDTO {

	private Long          id;
	private String        descricao;
		
	public CidadeDTO() {
	}
	
	public CidadeDTO(Object[] colunas) {
		this.id                 = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.descricao          = (String) colunas[1];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
