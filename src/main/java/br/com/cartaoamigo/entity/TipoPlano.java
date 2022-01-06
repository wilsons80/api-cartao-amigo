package br.com.cartaoamigo.entity;

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
@Table(name = "tipo_plano")
public class TipoPlano {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_plano")
	@SequenceGenerator(name = "sq_id_tipo_plano", sequenceName = "sq_id_tipo_plano", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tipo_plano")
	private Long id;

	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "tp_pagamento")
	private String tipoPagamento;
	
	@Column(name = "qtd_parcelas")
	private Long quantidadeParcelas;
	
	@Column(name = "qtd_dias_vigencia")
	private Long quantidadeDiasVigencia;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Column(name = "id_plano_pagarme")
	private String idPlanoPagarme;
	
	@Column(name="is_recorrencia")
	@Convert(converter = SimNaoConverter.class)
	private Boolean isRecorrencia;
	
	public TipoPlano() {
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Long getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Long quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getQuantidadeDiasVigencia() {
		return quantidadeDiasVigencia;
	}

	public void setQuantidadeDiasVigencia(Long quantidadeDiasVigencia) {
		this.quantidadeDiasVigencia = quantidadeDiasVigencia;
	}

	public String getIdPlanoPagarme() {
		return idPlanoPagarme;
	}

	public void setIdPlanoPagarme(String idPlanoPagarme) {
		this.idPlanoPagarme = idPlanoPagarme;
	}

	public Boolean getIsRecorrencia() {
		return isRecorrencia;
	}

	public void setIsRecorrencia(Boolean isRecorrencia) {
		this.isRecorrencia = isRecorrencia;
	}
	
	

}