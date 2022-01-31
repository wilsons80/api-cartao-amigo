package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

import br.com.cartaoamigo.infra.util.DataUtil;

public class CarteiraCartaoPagamentoAssociadoTO {
	
	private Long id;
	private Long idTitular;
	private String idClientePagarMe;
	private String idCartaoPagarMe;
	private LocalDateTime dataCriacao;
	private String mesValidade;
	private String anoValidade;
	private String bandeira;
	private String primeiros6digitos;
	private String ultimos4digitos;
	private boolean expirado;
	private Boolean exclusaoLogica;
	
	public CarteiraCartaoPagamentoAssociadoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public String getIdClientePagarMe() {
		return idClientePagarMe;
	}

	public void setIdClientePagarMe(String idClientePagarMe) {
		this.idClientePagarMe = idClientePagarMe;
	}

	public String getIdCartaoPagarMe() {
		return idCartaoPagarMe;
	}

	public void setIdCartaoPagarMe(String idCartaoPagarMe) {
		this.idCartaoPagarMe = idCartaoPagarMe;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getMesValidade() {
		return mesValidade;
	}

	public void setMesValidade(String mesValidade) {
		this.mesValidade = mesValidade;
	}

	public String getAnoValidade() {
		return anoValidade;
	}

	public void setAnoValidade(String anoValidade) {
		this.anoValidade = anoValidade;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getPrimeiros6digitos() {
		return primeiros6digitos;
	}

	public void setPrimeiros6digitos(String primeiros6digitos) {
		this.primeiros6digitos = primeiros6digitos;
	}

	public String getUltimos4digitos() {
		return ultimos4digitos;
	}

	public void setUltimos4digitos(String ultimos4digitos) {
		this.ultimos4digitos = ultimos4digitos;
	}

	public boolean expirado() {
		int mesCartao = Integer.valueOf(getMesValidade());
		int anoCartao = Integer.valueOf(getAnoValidade());
		expirado = DataUtil.isDataExpirada(mesCartao, anoCartao);
		
		return expirado;
	}

	public Boolean getExclusaoLogica() {
		return exclusaoLogica;
	}

	public void setExclusaoLogica(Boolean exclusaoLogica) {
		this.exclusaoLogica = exclusaoLogica;
	}
		
	
}
