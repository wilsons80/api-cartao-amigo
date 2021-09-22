package br.com.cartaoamigo.to;

import java.io.Serializable;

public class ClinicasTipoEspecialidadesTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private ClinicaTO clinica;
	private TipoEspecialidadeTO tipoEspecialidade;
	private Double valorParticular;
	private Double valorAssociado;
	private Boolean ativo;
	
	public ClinicasTipoEspecialidadesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClinicaTO getClinica() {
		return clinica;
	}

	public void setClinica(ClinicaTO clinica) {
		this.clinica = clinica;
	}

	public TipoEspecialidadeTO getTipoEspecialidade() {
		return tipoEspecialidade;
	}

	public void setTipoEspecialidade(TipoEspecialidadeTO tipoEspecialidade) {
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


