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
@Table(name = "tipo_usuario")
public class TipoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_usuario")
	@SequenceGenerator(name = "sq_id_tipo_usuario", sequenceName = "sq_id_tipo_usuario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tipo_usuario")
	private Long id;

	@Column(name = "ds_tipo_usuario")
	private String descricao;
	
	
	public TipoUsuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}