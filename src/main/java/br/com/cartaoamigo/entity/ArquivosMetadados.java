package br.com.cartaoamigo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

/**
 * The persistent class for the arquivos database table.
 * 
 */
@Entity
@Table(name = "arquivos_metadados")
public class ArquivosMetadados implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_arquivo_metadado") 
	@SequenceGenerator(name = "sq_id_arquivo_metadado", sequenceName = "sq_id_arquivo_metadado", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1) 
	@Column(name = "id_arquivo_metadado", unique = true, nullable = false, precision = 10) 
	private Long id;

	@Column(name = "nm_arquivo") 
	private String nmArquivo;

	@Column(name = "ds_tipo_arquivo") 
	private String dsTipoArquivo;

	@Column(name = "nr_tamanho_arquivo") 
	private Long nrTamanhoArquivo;

	@Column(name = "hash") 
	private String hash;

	@Column(name = "dt_criacao") 
	private LocalDateTime dtCriacao;

	@Column(name = "id_usuario_apl") 
	private Long usuarioAlteracao;

	public ArquivosMetadados() {
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