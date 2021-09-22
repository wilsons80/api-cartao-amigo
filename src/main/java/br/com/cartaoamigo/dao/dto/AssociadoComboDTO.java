package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class AssociadoComboDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	
	public AssociadoComboDTO() {
	}
	
	public AssociadoComboDTO(Object[] colunas) {
		this.id      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome    = (String) colunas[1];
		this.cpf    = (String) colunas[2];
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
