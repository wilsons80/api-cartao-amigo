package br.com.cartaoamigo.to;

import java.io.Serializable;

public class ArquivoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private byte[] blob;
	private String url;
	private ArquivosMetadadosTO metadados;

	public ArquivoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getBlob() {
		return blob;
	}

	public void setBlob(byte[] blob) {
		this.blob = blob;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArquivosMetadadosTO getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivosMetadadosTO metadados) {
		this.metadados = metadados;
	}



}