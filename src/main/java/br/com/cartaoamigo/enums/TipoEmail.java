package br.com.cartaoamigo.enums;

public enum TipoEmail {

	// @formatter:off
	CONTA_CRIADA 	        (1L, "Conta criada", "CONTA_CRIADA"), 
	REDEFINIR_SENHA   	    (2L, "Redefinir Senha", "REDEFINIR_SENHA"),
	PAGAMENTO        	    (3L, "Pagamento Efetuado", "PAGAMENTO"),
	CANCELAMENTO_ASSINATURA	(4L, "Assinatura Cancelada", "CANCELAMENTO_ASSINATURA");
	// @formatter:on

	private Long id;
	private String descricao;
	private String codigo;

	private TipoEmail(Long id, String codigo, String descricao) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String tipo) {
		this.codigo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoEmail getPorCodigo(String codigo) {
		for (TipoEmail tipoEmail : values()) {
			if (tipoEmail.getCodigo().equals(codigo)) {
				return tipoEmail;
			}
		}
		return null;
	}

	public static TipoEmail getPorId(Long id) {
		for (TipoEmail tipoEmail : values()) {
			if (tipoEmail.getId().equals(id)) {
				return tipoEmail;
			}
		}
		return null;
	}
}
