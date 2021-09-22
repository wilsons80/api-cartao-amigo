package br.com.cartaoamigo.to.relatorios;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ImpressaoCartaoTO {
	
	private Long idCartao;
	private String nomeAssociado;
	private String numeroCartao;
	private Boolean ativo;
	private Boolean isTitular;
	private LocalDate dataImpressao;
	private LocalDateTime dataGeracao;
	private String linkUrlQrcode;
	private String statusPagamento;
	private LocalDate dataFimValidade;
	
	public ImpressaoCartaoTO() {
	}

	public Long getIdCartao() {
		return idCartao;
	}

	public void setIdCartao(Long idCartao) {
		this.idCartao = idCartao;
	}

	public String getNomeAssociado() {
		return nomeAssociado;
	}

	public void setNomeAssociado(String nomeAssociado) {
		this.nomeAssociado = nomeAssociado;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getIsTitular() {
		return isTitular;
	}

	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	public LocalDate getDataImpressao() {
		return dataImpressao;
	}

	public void setDataImpressao(LocalDate dataImpressao) {
		this.dataImpressao = dataImpressao;
	}

	public String getLinkUrlQrcode() {
		return linkUrlQrcode;
	}

	public void setLinkUrlQrcode(String linkUrlQrcode) {
		this.linkUrlQrcode = linkUrlQrcode;
	}

	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public LocalDate getDataFimValidade() {
		return dataFimValidade;
	}

	public void setDataFimValidade(LocalDate dataFimValidade) {
		this.dataFimValidade = dataFimValidade;
	}


	
}
