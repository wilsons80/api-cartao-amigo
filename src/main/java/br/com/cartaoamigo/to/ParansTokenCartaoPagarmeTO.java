package br.com.cartaoamigo.to;

public class ParansTokenCartaoPagarmeTO {
	private String numeroCartao;
	private String nomeImpresso;
	private String cvv;
	private String mesVencimentoCartao;
	private String anoVencimentoCartao;

	public ParansTokenCartaoPagarmeTO() {
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeImpresso() {
		return nomeImpresso;
	}

	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getMesVencimentoCartao() {
		return mesVencimentoCartao;
	}

	public void setMesVencimentoCartao(String mesVencimentoCartao) {
		this.mesVencimentoCartao = mesVencimentoCartao;
	}

	public String getAnoVencimentoCartao() {
		return anoVencimentoCartao;
	}

	public void setAnoVencimentoCartao(String anoVencimentoCartao) {
		this.anoVencimentoCartao = anoVencimentoCartao;
	}

}
