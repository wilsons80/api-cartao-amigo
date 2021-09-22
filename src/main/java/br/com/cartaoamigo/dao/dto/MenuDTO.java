package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class MenuDTO {
	
	private Long idModulo;
	private String nomeModulo;

	public MenuDTO() {
	}
	
	public MenuDTO(Object[] colunas) {
		this.idModulo        = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nomeModulo      = (String) colunas[1];
		
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

}
