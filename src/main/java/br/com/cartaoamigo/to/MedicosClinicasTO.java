package br.com.cartaoamigo.to;

public class MedicosClinicasTO {
	private Long id;
	private MedicoTO medico;
	private ClinicaTO clinica;
	private Boolean ativo;
	
	public MedicosClinicasTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicoTO getMedico() {
		return medico;
	}

	public void setMedico(MedicoTO medico) {
		this.medico = medico;
	}

	public ClinicaTO getClinica() {
		return clinica;
	}

	public void setClinica(ClinicaTO clinica) {
		this.clinica = clinica;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
