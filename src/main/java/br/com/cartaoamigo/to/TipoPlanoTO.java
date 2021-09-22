package br.com.cartaoamigo.to;

public class TipoPlanoTO {
	private long id;
	private String descricao;
	private Double valor;
	private String tipoPagamento;
	private Long quantidadeParcelas;
	private Long quantidadeDiasVigencia;
	private Boolean ativo;
	
	public TipoPlanoTO() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Long getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Long quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getQuantidadeDiasVigencia() {
		return quantidadeDiasVigencia;
	}

	public void setQuantidadeDiasVigencia(Long quantidadeDiasVigencia) {
		this.quantidadeDiasVigencia = quantidadeDiasVigencia;
	}
	
	
}
