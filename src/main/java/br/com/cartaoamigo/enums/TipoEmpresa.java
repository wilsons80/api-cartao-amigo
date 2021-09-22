package br.com.cartaoamigo.enums;

public enum TipoEmpresa {

	// P = PARCEIRA; V = CONVÊNIO; T = CONTRATO; F = FORNECEDOR; C = CLIENTE; O =
	// OUTRO)

	// @formatter:off
	PARCEIRA 	                (1, "P", "Parceria"), 
	CONVÊNIO					(2, "V", "Convênio"), 
	CONTRATO					(3, "T", "Contrato"),
	FORNECEDOR					(4, "F", "Fornecedor"), 
	CLIENTE						(5, "C", "Cliente"),
	PARCEIRACLIENTE				(6, "L", "Parceria e Cliente"), 
	PARCEIRAFORNECEDOR			(7, "R", "Parceria e Fornecedor"),
	PARCEIRACLIENTEFORNECEDOR	(8, "E", "Parceria, Cliente e Fornecedor"), 
	FORNECEDORCLIENTE			(9, "N", "Fornecedor e Cliente"), 
	OUTRO						(10, "O", "Outro");
	// @formatter:on

	private Integer id;
	private String tipo;
	private String descricao;

	private TipoEmpresa(Integer id, String tipo, String descricao) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoEmpresa getPorTipo(String tipo) {
		for (TipoEmpresa tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
