package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class ModuloAcessoDTO {

	private Long idModulo;
	private String nome;
	
	public ModuloAcessoDTO() {
	}
	
	public ModuloAcessoDTO(Object[] colunas) {
		this.idModulo      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome          = (String) colunas[1];
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
