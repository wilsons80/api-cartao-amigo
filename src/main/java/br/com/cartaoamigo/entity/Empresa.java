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

/**
 * The persistent class for the empresas database table.
 * 
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_empresa")
	@SequenceGenerator(name = "sq_id_empresa", sequenceName = "sq_id_empresa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_empresa")
	private Long id;

	@Column(name = "cd_empresa")
	private String codigo;

	@Column(name = "nm_empresa")
	private String nomeEmpresa;
	
	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "ds_bairro")
	private String bairro;

	@Column(name = "nm_cidade")
	private String cidade;

	@Column(name = "nr_cep")
	private Long cep;
	
	@Column(name = "uf_endereco")
	private String uf;
	
	@Column(name = "nr_telefone")
	private String telefone;
	
	@Column(name = "nr_fone_celular")
	private String celular;
	
	@Column(name = "nm_fantasia")
	private String nomeFantasia;
	
	@Column(name = "nr_cnpj")
	private String cnpj;
	
	@Column(name = "ds_home_page")
	private String homePage;
	
	@Column(name = "nr_inscricao_estadual")
	private String inscricaoEstadual;
	
	@Column(name = "nr_inscricao_municipal")
	private String inscricaoMunicipal;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	
	@Column(name = "ds_email")
	private String email;
	
	@Column(name = "ds_visao")
	private String visao;

	@Column(name = "ds_missao")
	private String missao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_arquivo_logomarca")
	private ArquivosMetadados metadados;


	public Empresa() {
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

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVisao() {
		return visao;
	}

	public void setVisao(String visao) {
		this.visao = visao;
	}

	public String getMissao() {
		return missao;
	}

	public void setMissao(String missao) {
		this.missao = missao;
	}

	public ArquivosMetadados getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivosMetadados metadados) {
		this.metadados = metadados;
	}

}