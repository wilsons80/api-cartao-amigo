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
@Table(name = "medicos_especialidades_clinicas")
public class MedicosEspecialiadadesClinicas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_medico_especialidade_clinica")
	@SequenceGenerator(name = "sq_id_medico_especialidade_clinica", sequenceName = "sq_id_medico_especialidade_clinica", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_medico_especialidade_clinica", unique = true, nullable = false, precision = 10)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico_especialidade")
	private MedicoEspecialidades medicoEspecialidades;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico_clinica")
	private MedicosClinicas medicosClinicas;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	
	public MedicosEspecialiadadesClinicas() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicoEspecialidades getMedicoEspecialidades() {
		return medicoEspecialidades;
	}

	public void setMedicoEspecialidades(MedicoEspecialidades medicoEspecialidades) {
		this.medicoEspecialidades = medicoEspecialidades;
	}

	public MedicosClinicas getMedicosClinicas() {
		return medicosClinicas;
	}

	public void setMedicosClinicas(MedicosClinicas medicosClinicas) {
		this.medicosClinicas = medicosClinicas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}