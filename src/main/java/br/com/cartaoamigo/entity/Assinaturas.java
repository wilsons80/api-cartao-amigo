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
@Table(name = "ASSINATURAS")
public class Assinaturas {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_assinatura")
	@SequenceGenerator(name = "sq_id_assinatura", sequenceName = "sq_id_assinatura", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_assinatura")
	private Long id;

	@Column(name = "codigo_assinatura")
	private String codigoAssinatura;

	@Column(name = "is_ativo")
	@Convert(converter = SimNaoConverter.class)
	private Boolean ativo;

	@Column(name = "dt_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "dt_cancelamento")
	private LocalDateTime dataCancelamento;

	@Column(name = "id_titular")
	private Long idTitular;

	@Column(name = "id_plano")
	private Long idPlano;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_forma_pagamento")
	private FormaPagamento formaPagamento;

	@Column(name = "id_cartao_pagarme")
	private String idCartaoPagarMe;
	
	
	public Assinaturas() {
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

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getIdCartaoPagarMe() {
		return idCartaoPagarMe;
	}

	public void setIdCartaoPagarMe(String idCartaoPagarMe) {
		this.idCartaoPagarMe = idCartaoPagarMe;
	}

	
}