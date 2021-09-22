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
@Table(name = "grupo_acesso_modulos")
public class GrupoAcessoModulos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_grupo_acesso_modulo")
	@SequenceGenerator(name = "sq_id_grupo_acesso_modulo", sequenceName = "sq_id_grupo_acesso_modulo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_grupo_acesso_modulo", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "id_grupo_acesso")
	private Long idGrupoAcesso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_modulo")
	private Modulo modulo;

	
	public GrupoAcessoModulos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdGrupoAcesso() {
		return idGrupoAcesso;
	}

	public void setIdGrupoAcesso(Long idGrupoAcesso) {
		this.idGrupoAcesso = idGrupoAcesso;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}	

}