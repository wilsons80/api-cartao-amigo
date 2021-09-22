package br.com.cartaoamigo.ws.pagseguro.to;

public class RetornoSplitPagamentoTO {

	private String codigoTransacao;
	private String statusTransacao;
	private String descricaoStatusTransacao;
	private String linkPagamento;

	public RetornoSplitPagamentoTO() {
	}

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}

	public String getStatusTransacao() {
		return statusTransacao;
	}

	public void setStatusTransacao(String statusTransacal) {
		this.statusTransacao = statusTransacal;
	}

	public String getDescricaoStatusTransacao() {
		return descricaoStatusTransacao;
	}

	public void setDescricaoStatusTransacao(String descricaoStatusTransacao) {
		this.descricaoStatusTransacao = descricaoStatusTransacao;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

}
