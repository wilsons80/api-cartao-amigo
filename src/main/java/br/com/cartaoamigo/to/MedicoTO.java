package br.com.cartaoamigo.to;

import java.util.List;

public class MedicoTO {
	
	private Long id;
	private String crm;
	private PessoaFisicaTO pessoaFisica;

	private List<MedicoEspecialidadesTO> especialidades;
	
 
	public MedicoTO() {
	}
	
	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<MedicoEspecialidadesTO> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<MedicoEspecialidadesTO> especialidades) {
		this.especialidades = especialidades;
	}
	
	
}
