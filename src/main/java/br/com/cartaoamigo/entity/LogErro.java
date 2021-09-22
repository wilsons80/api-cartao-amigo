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
@Table(name = "LOG_ERRO")
public class LogErro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_logerro")
	@SequenceGenerator(name = "sq_id_logerro", sequenceName = "sq_id_logerro", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "idlogerro", nullable = false, unique = true, insertable = false, updatable = false)
	private Integer id;

	@Column(name = "txmensagemerro", nullable = false)
	private String mensagemErro;

	@Column(name = "dslogin", nullable = false, length = 32)
	private String login;

	@Column(name = "dtregistro", nullable = false)
	private LocalDateTime dataRegistro;

	public LogErro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

}
