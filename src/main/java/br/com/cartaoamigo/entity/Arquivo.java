package br.com.cartaoamigo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

/**
 * The persistent class for the arquivos database table.
 * 
 */
@Entity
@Table(name = "arquivos")
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_arquivo")
	@SequenceGenerator(name = "sq_id_arquivo", sequenceName = "sq_id_arquivo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_arquivo", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "blob")
	private byte[] blob;
	
	@Column(name = "tx_url")
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_arquivo_metadado")
	private ArquivosMetadados metadados;

	public Arquivo() {
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

	public ArquivosMetadados getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivosMetadados metadados) {
		this.metadados = metadados;
	}

}