package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

@Entity
@Table(name = "redefinir_senha")
public class RedefinirSenha {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_redefinir_senha")
	@SequenceGenerator(name = "sq_id_redefinir_senha", sequenceName = "sq_id_redefinir_senha", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_redefinir_senha", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "codigo_validacao")
	private String codigoValidacao;

	@Column(name = "dt_validade")
	private LocalDateTime dataValidade;

	@Column(name = "link")
	private String link;
	
	@Column(name = "email")
	private String email;
	
	
	public RedefinirSenha() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoValidacao() {
		return codigoValidacao;
	}

	public void setCodigoValidacao(String codigoValidacao) {
		this.codigoValidacao = codigoValidacao;
	}

	public LocalDateTime getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDateTime dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}