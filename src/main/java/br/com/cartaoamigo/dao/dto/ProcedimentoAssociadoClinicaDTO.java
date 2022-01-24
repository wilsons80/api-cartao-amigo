package br.com.cartaoamigo.dao.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProcedimentoAssociadoClinicaDTO {

	private String        nomeImpresso;
	private String        nomeClinica;
	private String        especialidade;
	private LocalDateTime dataConsulta;
	private Boolean       assinaturaAtiva;
	
	public ProcedimentoAssociadoClinicaDTO() {
	}
	
	public ProcedimentoAssociadoClinicaDTO(Object[] colunas) {
		this.nomeImpresso            = (String) colunas[0];
		this.nomeClinica             = (String) colunas[1];
		this.especialidade           = (String) colunas[2];
		this.dataConsulta            = (colunas[3] != null)? ((Timestamp)colunas[3]).toLocalDateTime() : null;
		this.assinaturaAtiva         = (colunas[4] != null)? ((String)colunas[4]).equals("S") : false;
	}

	public String getNomeImpresso() {
		return nomeImpresso;
	}

	public void setNomeImpresso(String numeroCartao) {
		this.nomeImpresso = numeroCartao;
	}

	public String getNomeClinica() {
		return nomeClinica;
	}

	public void setNomeClinica(String nomeClinica) {
		this.nomeClinica = nomeClinica;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Boolean getAssinaturaAtiva() {
		return assinaturaAtiva;
	}

	public void setAssinaturaAtiva(Boolean assinaturaAtiva) {
		this.assinaturaAtiva = assinaturaAtiva;
	}
	
	
}
