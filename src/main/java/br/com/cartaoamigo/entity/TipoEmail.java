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
@Table(name = "tipo_email")
public class TipoEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_email")
	@SequenceGenerator(name = "sq_id_tipo_email", sequenceName = "sq_id_tipo_email", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tipo_email", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "desc_tipo")
	private String descricao;
	
	
	public TipoEmail() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

}