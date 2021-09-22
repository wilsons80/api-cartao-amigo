package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

@Entity
@Table(name = "AUTORIZACAO_GATEWAY")
public class AutorizacaoGateway {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_autorizacao_gateway")
	@SequenceGenerator(name = "sq_id_autorizacao_gateway", sequenceName = "sq_id_autorizacao_gateway", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_autorizacao_gateway")
	private Long id;

	@Column(name = "dt_autorizacao")
	private LocalDateTime dataAutorizacao;

	@Column(name = "codigo_autorizacao")
	private String codigoAutorizacao;
	
	@Column(name = "link")
	private String url;

	public AutorizacaoGateway() {
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
