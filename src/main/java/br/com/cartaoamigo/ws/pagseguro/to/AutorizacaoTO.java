package br.com.cartaoamigo.ws.pagseguro.to;

import java.time.LocalDateTime;

public class AutorizacaoTO {

	private Long id;
	private String codigoAutorizacao;
	private LocalDateTime dataAutorizacao;
	private String urlAutorizacao;

	public AutorizacaoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public LocalDateTime getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(LocalDateTime dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public String getUrlAutorizacao() {
		return urlAutorizacao;
	}

	public void setUrlAutorizacao(String urlAutorizacao) {
		this.urlAutorizacao = urlAutorizacao;
	}

}
