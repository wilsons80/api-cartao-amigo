package br.com.cartaoamigo.entity;

import java.io.Serializable;

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
@Table(name = "clinicas_tipo_especialidades")
public class ClinicasTipoEspecialidades implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_clinica_tipo_especidades")
	@SequenceGenerator(name = "sq_id_clinica_tipo_especidades", sequenceName = "sq_id_clinica_tipo_especidades", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_clinica_tipo_especidades", unique = true, nullable = false, precision = 10)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clinica")
	private Clinica clinica;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_especialidade")
	private TipoEspecialidade tipoEspecialidade;
	
	@Column(name = "vl_particular")
	private Double valorParticular;

	@Column(name = "vl_associado")
	private Double valorAssociado;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	
	public ClinicasTipoEspecialidades() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public TipoEspecialidade getTipoEspecialidade() {
		return tipoEspecialidade;
	}

	public void setTipoEspecialidade(TipoEspecialidade tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}

	public Double getValorParticular() {
		return valorParticular;
	}

	public void setValorParticular(Double valorParticular) {
		this.valorParticular = valorParticular;
	}

	public Double getValorAssociado() {
		return valorAssociado;
	}

	public void setValorAssociado(Double valorAssociado) {
		this.valorAssociado = valorAssociado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}