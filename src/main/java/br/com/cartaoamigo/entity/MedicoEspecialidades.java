package br.com.cartaoamigo.entity;

import java.io.Serializable;

import javax.persistence.Column;
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


@Entity
@Table(name = "medico_especialidades")
public class MedicoEspecialidades implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_medico_especialidade")
	@SequenceGenerator(name = "sq_id_medico_especialidade", sequenceName = "sq_id_medico_especialidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_medico_especialidade", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "id_medico")
	private Long idMedico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_especialidade")
	private TipoEspecialidade tipoEspecialidade;
	
	public MedicoEspecialidades() {
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

	public TipoEspecialidade getTipoEspecialidade() {
		return tipoEspecialidade;
	}

	public void setTipoEspecialidade(TipoEspecialidade tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}


}