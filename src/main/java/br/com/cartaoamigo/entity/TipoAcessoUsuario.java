package br.com.cartaoamigo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

@Entity
@Table(name="TIPO_ACESSO_USUARIOS")
public class TipoAcessoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_acesso_usuario")
	@SequenceGenerator(name = "sq_id_tipo_acesso_usuario", sequenceName = "sq_id_tipo_acesso_usuario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_tipo_acesso_usuario")
	private Long id;

	@Column(name="id_usuario")
	private Long idUsuario;
	
	@Column(name="id_tipo_usuario")
	private Long idTipoUsuario;

	
	public TipoAcessoUsuario() {
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

	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}



}