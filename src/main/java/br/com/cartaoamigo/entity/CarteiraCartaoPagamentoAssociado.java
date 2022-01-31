package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;
import br.com.cartaoamigo.infra.dao.SimNaoConverter;

@Entity
@Table(name = "CARTEIRA_CARTAO_PAGAMENTO_ASSOCIADO")
public class CarteiraCartaoPagamentoAssociado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_carteira_cartao_pagamento_associado")
	@SequenceGenerator(name = "sq_id_carteira_cartao_pagamento_associado", sequenceName = "sq_id_carteira_cartao_pagamento_associado", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_carteira_cartao_pagamento_associado")
	private Long id;

	@Column(name = "id_titular")
	private Long idTitular;

	@Column(name = "customer_id")
	private String idClientePagarMe;

	@Column(name = "card_id")
	private String idCartaoPagarMe;

	@Column(name = "dt_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "mes_validade")
	private String mesValidade;

	@Column(name = "ano_validade")
	private String anoValidade;

	@Column(name = "bandeira")
	private String bandeira;

	@Column(name = "primeiros6digitos")
	private String primeiros6digitos;

	@Column(name = "ultimos4digitos")
	private String ultimos4digitos;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "exclusao_logica")
	private Boolean exclusaoLogica;

	public CarteiraCartaoPagamentoAssociado() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public String getIdClientePagarMe() {
		return idClientePagarMe;
	}

	public void setIdClientePagarMe(String idClientePagarMe) {
		this.idClientePagarMe = idClientePagarMe;
	}

	public String getIdCartaoPagarMe() {
		return idCartaoPagarMe;
	}

	public void setIdCartaoPagarMe(String idCartaoPagarMe) {
		this.idCartaoPagarMe = idCartaoPagarMe;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getMesValidade() {
		return mesValidade;
	}

	public void setMesValidade(String mesValidade) {
		this.mesValidade = mesValidade;
	}

	public String getAnoValidade() {
		return anoValidade;
	}

	public void setAnoValidade(String anoValidade) {
		this.anoValidade = anoValidade;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getPrimeiros6digitos() {
		return primeiros6digitos;
	}

	public void setPrimeiros6digitos(String primeiros6digitos) {
		this.primeiros6digitos = primeiros6digitos;
	}

	public String getUltimos4digitos() {
		return ultimos4digitos;
	}

	public void setUltimos4digitos(String ultimos4digitos) {
		this.ultimos4digitos = ultimos4digitos;
	}

	public Boolean getExclusaoLogica() {
		return exclusaoLogica;
	}

	public void setExclusaoLogica(Boolean exclusaoLogica) {
		this.exclusaoLogica = exclusaoLogica;
	}


}
