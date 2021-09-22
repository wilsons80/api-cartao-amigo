package br.com.cartaoamigo.enums;

public enum TipoUsuarioSistema {

	// @formatter:off
	ASSOCIADO_TITULAR 	    (1L, "ASSOCIADO_TITULAR", "Titular"), 
	ASSOCIADO_DEPENDENTE	(2L, "ASSOCIADO_DEPENDENTE", "Dependente"),
	CLINICA					(3L, "CLINICA", "Clínica"), 
	ADMINISTRATIVO			(4L, "ADMINISTRATIVO", "Administrativo"),
	ROOT					(5L, "ROOT", "Root");
	// @formatter:on

	private Long id;
	private String tipo;
	private String descricao;

	private TipoUsuarioSistema(Long id, String tipo, String descricao) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public static TipoUsuarioSistema getPorId(Long id) {
		for (TipoUsuarioSistema tipoClassificador : values()) {
			if (tipoClassificador.getId().equals(id)) {
				return tipoClassificador;
			}
		}
		return null;
	}

	public static TipoUsuarioSistema getPorTipo(String tipo) {
		for (TipoUsuarioSistema tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
