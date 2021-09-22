package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.cartaoamigo.infra.dao.SimNaoConverter;

@Entity
@Table(name = "envio_email")
public class EnvioEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_envio_email")
	@SequenceGenerator(name = "sq_id_envio_email", sequenceName = "sq_id_envio_email", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_envio_email", unique = true, nullable = false, precision = 10)
	private Long id;
	
	@Column(name="st_envio")
	@Convert(converter = SimNaoConverter.class)
	private Boolean enviado;

	@Column(name = "dt_envio")
	private LocalDateTime dataEnvio;

	@Column(name = "dt_criacao")
	private LocalDateTime dataCriacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa_fisica")
	private PessoaFisica pessoaFisica;
	
	@Column(name = "id_tipo_email")
	private Long idTipoEmail;

	@Column(name = "senha_provisoria")
	private String  senhaProvisoria;
	
	@Column(name = "link_pagamento")
	private String  linkPagamento;
	
	@Column(name = "id_tipo_plano")
	private Long idTipoPlano;
	
	@Column(name="st_titular")
	@Convert(converter = SimNaoConverter.class)
	private Boolean isTitular;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "id_historico_pagamento")
	private Long idHistoricoPagamento;
	

	public EnvioEmail() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getIdTipoEmail() {
		return idTipoEmail;
	}

	public void setIdTipoEmail(Long idTipoEmail) {
		this.idTipoEmail = idTipoEmail;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getSenhaProvisoria() {
		return senhaProvisoria;
	}

	public void setSenhaProvisoria(String senhaProvisoria) {
		this.senhaProvisoria = senhaProvisoria;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

	public Boolean getIsTitular() {
		return isTitular;
	}

	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	public Long getIdTipoPlano() {
		return idTipoPlano;
	}

	public void setIdTipoPlano(Long idTipoPlanol) {
		this.idTipoPlano = idTipoPlanol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getIdHistoricoPagamento() {
		return idHistoricoPagamento;
	}

	public void setIdHistoricoPagamento(Long idHistoricoPagamento) {
		this.idHistoricoPagamento = idHistoricoPagamento;
	}

}