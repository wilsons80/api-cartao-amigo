package br.com.cartaoamigo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

@Entity
@Table(name = "status_transacao_gateway_pagamento")
public class StatusTransacaoGatewayPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_status_transacao")
	@SequenceGenerator(name = "sq_id_status_transacao", sequenceName = "sq_id_status_transacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_status_transacao")
	private Long id;

	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_gateway_pagamento")
	private GatewayPagamento gatewayPagamento ;
	
	@Column(name = "codigo_transacao")
	private String codigoTransacao;

	public StatusTransacaoGatewayPagamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public void setGatewayPagamento(GatewayPagamento gatewaypagamento) {
		this.gatewayPagamento = gatewaypagamento;
	}

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigotransacao) {
		this.codigoTransacao = codigotransacao;
	}

	@Override
	public String toString() {
		return "StatusTransacaoGatewayPagamento [id=" + id + ", descricao=" + descricao + ", gatewayPagamento="
				+ gatewayPagamento + ", codigoTransacao=" + codigoTransacao + "]";
	}

	
}