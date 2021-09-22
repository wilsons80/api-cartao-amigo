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


@Entity
@Table(name = "clinicas")
public class Clinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_clinica")
	@SequenceGenerator(name = "sq_id_clinica", sequenceName = "sq_id_clinica", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_clinica", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "cd_clinica")
	private String codigo;

	@Column(name = "nm_fantasia")
	private String nomeFantasia;
	
	@Column(name = "nm_razao_social")
	private String nomeRazaoSocial;

	@Column(name = "ds_endereco")
	private String endereco;
	
	@Column(name = "nr_cep")
	private Long cep;
	
	@Column(name = "ds_bairro")
	private String bairro;
	
	@Column(name = "nm_cidade")
	private String cidade;
	
	@Column(name = "uf_endereco")
	private String uf;
	
	@Column(name = "nr_telefone01")
	private String telefone01;
	
	@Column(name = "nr_telefone02")
	private String telefone02;
	
	@Column(name = "nr_fone_celular")
	private String celular;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_arquivo_logomarca")
	private ArquivosMetadados metadados;
	
	@Column(name = "ds_email")
	private String email;

	@Column(name = "nr_cnpj")
	private String cnpj;

	@Column(name = "nr_inscricao_estadual")
	private String inscricaoEstadual;

	@Column(name = "nr_inscricao_municipal")
	private String inscricaoMunicipal;

	@Column(name = "ds_home_page")
	private String homePage;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Column(name = "id_usuario_apl")
	private Long idUsuarioApl;
	
	@Column(name = "nr_endereco")
	private String numeroEndereco;

	@Column(name = "complemento" )
	private String complemento;
	
	@Column(name = "dt_cadastro" )
	private LocalDateTime dataCadastro;
	
	
	public Clinica() {
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
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

	public String getTelefone01() {
		return telefone01;
	}

	public void setTelefone01(String telefone01) {
		this.telefone01 = telefone01;
	}

	public String getTelefone02() {
		return telefone02;
	}

	public void setTelefone02(String telefone02) {
		this.telefone02 = telefone02;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public ArquivosMetadados getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivosMetadados metadados) {
		this.metadados = metadados;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getIdUsuarioApl() {
		return idUsuarioApl;
	}

	public void setIdUsuarioApl(Long idUsuarioApl) {
		this.idUsuarioApl = idUsuarioApl;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	

}