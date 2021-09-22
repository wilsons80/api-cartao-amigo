package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class ClinicaComboDTO {
	
	private Long id;
	private String nome;
	
	
	public ClinicaComboDTO() {
	}
	
	public ClinicaComboDTO(Object[] colunas) {
		this.id      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome    = (String) colunas[1];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
