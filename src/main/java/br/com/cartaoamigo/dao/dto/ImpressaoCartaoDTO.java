package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ImpressaoCartaoDTO {
	
	private Long idCartao;
	private String nomeAssociado;
	private String numeroCartao;
	private Boolean ativo;
	private Boolean isTitular;
	private LocalDate dataImpressao;
	private String linkUrlQrcode;
	private LocalDateTime dataGeracao;
	private Long idTitular;
	private LocalDate dataFimValidade;
	
	public ImpressaoCartaoDTO() {
	}
	
	public ImpressaoCartaoDTO(Object[] colunas) {
		this.idCartao                  = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nomeAssociado             = (colunas[1] != null) ? (String) colunas[1] : "";
		this.numeroCartao              = (colunas[2] != null) ? (String) colunas[2] : "";		
		this.ativo                     = (colunas[3] != null) ? ((String) colunas[3]).equals("S") : false;
		this.isTitular                 = (colunas[4] != null) ? ((String) colunas[4]).equals("S") : false;
		this.dataImpressao             = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime().toLocalDate() : null;
		this.linkUrlQrcode             = (colunas[6] != null) ? (String) colunas[6] : "";
		this.dataGeracao               = (colunas[7] != null)? ((Timestamp)colunas[7]).toLocalDateTime() : null;
		this.idTitular                 = (colunas[8] != null)? ((BigDecimal)colunas[8]).longValue() : null;
		this.dataFimValidade           = (colunas[9] != null)? ((Timestamp)colunas[9]).toLocalDateTime().toLocalDate() : null;
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

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public LocalDate getDataFimValidade() {
		return dataFimValidade;
	}

	public void setDataFimValidade(LocalDate dataFimValidade) {
		this.dataFimValidade = dataFimValidade;
	}
	
}
