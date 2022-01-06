package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class AssinaturasTO {

	private Long id;
	private String codigoAssinatura;
	private Boolean ativo;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataCancelamento;
	private Long idTitular;
	private Long idPlano;

	public AssinaturasTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoAssinatura() {
		return codigoAssinatura;
	}

	public void setCodigoAssinatura(String codigoAssinatura) {
		this.codigoAssinatura = codigoAssinatura;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean isAtivo) {
		this.ativo = isAtivo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}

}
