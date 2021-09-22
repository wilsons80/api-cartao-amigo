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
@Table(name = "perfil_acesso_usuario")
public class PerfilAcessoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_perfil_acesso_usuario")
	@SequenceGenerator(name = "sq_id_perfil_acesso_usuario", sequenceName = "sq_id_perfil_acesso_usuario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_perfil_acesso_usuario", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "id_usuario")
	private Long idUsuario;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo_acesso")
	private GrupoAcesso grupoAcesso;
	
	public PerfilAcessoUsuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public GrupoAcesso getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(GrupoAcesso grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}


}