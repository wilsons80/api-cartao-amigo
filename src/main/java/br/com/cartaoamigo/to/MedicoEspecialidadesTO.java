package br.com.cartaoamigo.to;

public class MedicoEspecialidadesTO {
	
	private Long id;
	private Long idMedico;
	private TipoEspecialidadeTO tipoEspecialidade;
	
	public MedicoEspecialidadesTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}

	public TipoEspecialidadeTO getTipoEspecialidade() {
		return tipoEspecialidade;
	}
	public void setTipoEspecialidade(TipoEspecialidadeTO tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}



}
