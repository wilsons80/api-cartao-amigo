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
@Table(name = "cartao")
public class Cartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cartao")
	@SequenceGenerator(name = "sq_id_cartao", sequenceName = "sq_id_cartao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_cartao", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "nr_cartao")
	private String numeroCartao;

	@Column(name = "nome_impresso")
	private String nomeImpresso;
	
	@Column(name = "url_qrcode")
	private String urlCode;
	
	@Column(name = "url_imagem_cartao")
	private String urlImagemCartao;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_foto_associado")
	private ArquivosMetadados metadados;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa_fisica")
	private PessoaFisica pessoaFisica;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_titular")
	private Boolean isTitular;
	
	@Column(name = "id_titular")
	private Long idTitular;

	@Column(name = "dt_impressao")
	private LocalDateTime dataImpressao;	
	
	@Column(name = "dt_criado")
	private LocalDateTime dataCriado;

	@Column(name = "dt_validade_plano")
	private LocalDateTime dataValidadePlano;
	
	
	public Cartao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeImpresso() {
		return nomeImpresso;
	}

	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public String getUrlImagemCartao() {
		return urlImagemCartao;
	}

	public void setUrlImagemCartao(String urlImagemCartao) {
		this.urlImagemCartao = urlImagemCartao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public ArquivosMetadados getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivosMetadados metadados) {
		this.metadados = metadados;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Boolean getIsTitular() {
		return isTitular;
	}

	public void setIsTitular(Boolean titular) {
		this.isTitular = titular;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public LocalDateTime getDataImpressao() {
		return dataImpressao;
	}

	public void setDataImpressao(LocalDateTime dataImpressao) {
		this.dataImpressao = dataImpressao;
	}

	public LocalDateTime getDataCriado() {
		return dataCriado;
	}

	public void setDataCriado(LocalDateTime dataCriado) {
		this.dataCriado = dataCriado;
	}

	public LocalDateTime getDataValidadePlano() {
		return dataValidadePlano;
	}

	public void setDataValidadePlano(LocalDateTime dataValidadePlano) {
		this.dataValidadePlano = dataValidadePlano;
	}

	
}