package br.com.cartaoamigo.enums;

public enum FormaPagamento {

	// Forma de venda do produto da atividade (D = DINHEIRO; C = CARTÃO; D = DÉBITO;
	// F = DESCONTO EM FOLHA

	DINHEIRO(1, "R", "Dinheiro"), CARTAO(2, "C", "Cartão"), DEBITO(3, "D", "Débito"),
	DESCONTO_FOLHA(4, "F", "Desconto em Folha");

	private Integer id;
	private String tipo;
	private String descricao;

	private FormaPagamento(Integer id, String tipo, String descricao) {
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

	public static FormaPagamento getPorTipo(String tipo) {
		for (FormaPagamento tipoClassificador : values()) {
			if (tipoClassificador.getTipo().equals(tipo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}
