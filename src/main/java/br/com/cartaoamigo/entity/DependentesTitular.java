package br.com.cartaoamigo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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

/**
 * The persistent class for the arquivos database table.
 * 
 */
@Entity
@Table(name = "dependentes_titular")
public class DependentesTitular implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_dependente")
	@SequenceGenerator(name = "sq_id_dependente", sequenceName = "sq_id_dependente", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_dependente", unique = true, nullable = false, precision = 10)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa_fisica")
	private PessoaFisica pessoaFisica;
	
	@Column(name = "id_titular")
	private Long  idTitular;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dtCadastro;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "ST_EXCLUSAO_LOGICA")
	private Boolean exclusaoLogica;
	
	
	public DependentesTitular() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Boolean getExclusaoLogica() {
		return exclusaoLogica;
	}

	public void setExclusaoLogica(Boolean exclusaoLogica) {
		this.exclusaoLogica = exclusaoLogica;
	}
	
	

}