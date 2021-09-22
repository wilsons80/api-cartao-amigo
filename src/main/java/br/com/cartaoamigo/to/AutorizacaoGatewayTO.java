package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class AutorizacaoGatewayTO {
	
	private Long id;
	private String codigoAutorizacao;
	private LocalDateTime dataAutorizacao;
	private String url;

	public AutorizacaoGatewayTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(LocalDateTime dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	
	
}
