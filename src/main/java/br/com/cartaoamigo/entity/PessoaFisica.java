package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

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

import org.apache.commons.lang3.StringUtils;

import br.com.cartaoamigo.infra.constantes.Constantes;
import br.com.cartaoamigo.infra.util.NumeroUtil;

@Entity
@Table(name = "pessoas_fisicas")
public class PessoaFisica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_pessoa_fisica")
	@SequenceGenerator(name = "sq_id_pessoa_fisica", sequenceName = "sq_id_pessoa_fisica", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_pessoa_fisica")
	private Long id;

	@Column(name = "nm_pessoa_fisica")
	private String nome;
	
	@Column(name = "nr_endereco")
	private String numeroEndereco;

	@Column(name = "complemento" )
	private String complemento;
	
	@Column(name = "ds_sexo")
	private String sexo;
	
	@Column(name = "nr_cpf")
	private String cpf;

	@Column(name = "nr_ci")
	private String numeroIdentidade;
	
	@Column(name = "cd_orgao_ci")
	private String orgaoIdentidade;
	
	@Column(name = "sg_uf_ci")
	private String ufIdentidade;
	
	@Column(name = "dt_emissao_ci")
	private LocalDateTime dataEmissaoIdentidade;
	
	@Column(name = "nm_mae")
	private String nomeMae;
	
	@Column(name = "nm_pai")
	private String nomePai;
	
	@Column(name = "dt_nascimento")
	private LocalDateTime dataNascimento;

	@Column(name = "sg_uf_nascimento")
	private String ufNascimento;
	
	@Column(name = "ds_estado_civil")
	private String estadoCivil;
	
	@Column(name = "ds_email")
	private String email;
		
	@Column(name = "nr_cep")
	private Long cep;
	
	@Column(name = "ds_endereco")
	private String endereco;
	
	@Column(name = "ds_bairro")
	private String bairro;

	@Column(name = "ds_cidade")
	private String cidade;

	@Column(name = "sg_uf_endereco")
	private String uf;

	@Column(name = "nr_fone_celular")
	private String celular;

	@Column(name = "nr_telefone_residencial")
	private String telefoneResidencial;
	
	@Column(name = "nr_telefone_comercial")
	private String telefoneComercial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_arquivo_foto")
	private ArquivosMetadados arquivoFoto;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	public PessoaFisica() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = StringUtils.isNotEmpty(cpf) ? NumeroUtil.somenteNumeros(cpf) : cpf;
	}

	public String getNumeroIdentidade() {
		return numeroIdentidade;
	}

	public void setNumeroIdentidade(String numeroIdentidade) {
		this.numeroIdentidade = numeroIdentidade;
	}

	public String getOrgaoIdentidade() {
		return orgaoIdentidade;
	}

	public void setOrgaoIdentidade(String orgaoIdentidade) {
		this.orgaoIdentidade = orgaoIdentidade;
	}

	public String getUfIdentidade() {
		return ufIdentidade;
	}

	public void setUfIdentidade(String ufIdentidade) {
		this.ufIdentidade = ufIdentidade;
	}

	public LocalDateTime getDataEmissaoIdentidade() {
		return dataEmissaoIdentidade;
	}

	public void setDataEmissaoIdentidade(LocalDateTime dataEmissaoIdentidade) {
		this.dataEmissaoIdentidade = dataEmissaoIdentidade;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getUfNascimento() {
		return ufNascimento;
	}

	public void setUfNascimento(String ufNascimento) {
		this.ufNascimento = ufNascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public ArquivosMetadados getArquivoFoto() {
		return arquivoFoto;
	}

	public void setArquivoFoto(ArquivosMetadados arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
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