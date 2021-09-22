package br.com.cartaoamigo.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ArquivosMetadadosTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nmArquivo;
	private String dsTipoArquivo;
	private Long nrTamanhoArquivo;
	private String hash;
	private LocalDateTime dtCriacao;
	private Long usuarioAlteracao;

	public ArquivosMetadadosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public String getDsTipoArquivo() {
		return dsTipoArquivo;
	}

	public void setDsTipoArquivo(String dsTipoArquivo) {
		this.dsTipoArquivo = dsTipoArquivo;
	}

	public Long getNrTamanhoArquivo() {
		return nrTamanhoArquivo;
	}

	public void setNrTamanhoArquivo(Long nrTamanhoArquivo) {
		this.nrTamanhoArquivo = nrTamanhoArquivo;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public LocalDateTime getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(LocalDateTime dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}



}