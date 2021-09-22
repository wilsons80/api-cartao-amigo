package br.com.cartaoamigo.ws.pagseguro.to;

import java.time.LocalDate;

public class CheckoutTransparenteCartaoCreditoTO {

	private Long   idPlano;
	private String cpfComprador;
	private String codigoCorretor;
	private String senderHash;
	private String tokenCartaoCredito;
	private String voucher;
	private String idSessao;
	private String bandeiraCartao;
	
	private String    nomeImpressoCartao;
	private String    cpfTitularCartao;
	private LocalDate dataNascimentoTitularCartao;
	
	
	public CheckoutTransparenteCartaoCreditoTO() {
	}

	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}

	public String getCpfComprador() {
		return cpfComprador;
	}

	public void setCpfComprador(String cpfComprador) {
		this.cpfComprador = cpfComprador;
	}

	public String getCodigoCorretor() {
		return codigoCorretor;
	}

	public void setCodigoCorretor(String codigoCorretor) {
		this.codigoCorretor = codigoCorretor;
	}

	public String getSenderHash() {
		return senderHash;
	}

	public void setSenderHash(String senderHash) {
		this.senderHash = senderHash;
	}

	public String getTokenCartaoCredito() {
		return tokenCartaoCredito;
	}

	public void setTokenCartaoCredito(String tokenCartaoCredito) {
		this.tokenCartaoCredito = tokenCartaoCredito;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

	public String getNomeImpressoCartao() {
		return nomeImpressoCartao;
	}

	public void setNomeImpressoCartao(String nomeImpressoCartao) {
		this.nomeImpressoCartao = nomeImpressoCartao;
	}

	public String getCpfTitularCartao() {
		return cpfTitularCartao;
	}

	public void setCpfTitularCartao(String cpfTitularCartao) {
		this.cpfTitularCartao = cpfTitularCartao;
	}

	public LocalDate getDataNascimentoTitularCartao() {
		return dataNascimentoTitularCartao;
	}

	public void setDataNascimentoTitularCartao(LocalDate dataNascimentoTitularCartao) {
		this.dataNascimentoTitularCartao = dataNascimentoTitularCartao;
	}


	
}
