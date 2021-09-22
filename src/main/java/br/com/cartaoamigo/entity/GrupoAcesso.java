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
@Table(name = "grupo_acesso")
public class GrupoAcesso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_grupo_acesso")
	@SequenceGenerator(name = "sq_id_grupo_acesso", sequenceName = "sq_id_grupo_acesso", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_grupo_acesso", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "ds_nome")
	private String nomeGrupoAcesso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil_acesso")
	private PerfilAcesso perfilAcesso;
	
	@Column(name="visivel")
	@Convert(converter = SimNaoConverter.class)
	private Boolean visivel;
	
	public GrupoAcesso() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeGrupoAcesso() {
		return nomeGrupoAcesso;
	}

	public void setNomeGrupoAcesso(String nome) {
		this.nomeGrupoAcesso = nome;
	}

	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public Boolean getVisivel() {
		return visivel;
	}

	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}

}