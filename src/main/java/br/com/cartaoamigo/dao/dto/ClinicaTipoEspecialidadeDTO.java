package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ClinicaTipoEspecialidadeDTO {

	private Long          idClinica;
	private String        nomeRazaoSocial;
	private String        email;
	private String        cnpj;
	private String        nomeFantasia;
	private Boolean       ativo;
	private LocalDateTime dataCadastro;
	private String        uf;
	private String        cidade;
	private String        bairro;
	private String 		  endereco;
	private String 		  telefone01;
	private String		  telefone02;
	private String 		  celular;
	private String        descricaoEspecialidade;
	
	
	public ClinicaTipoEspecialidadeDTO() {
	}
	
	public ClinicaTipoEspecialidadeDTO(Object[] colunas) {
		this.idClinica               = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nomeRazaoSocial         = (String) colunas[1];
		this.email                   = (String) colunas[2];
		this.cnpj                    = (String) colunas[3];
		this.nomeFantasia            = (String) colunas[4];
		this.ativo                   = (colunas[5] != null) ? ((String)colunas[5]).toUpperCase().equals("S") : false;
		this.dataCadastro            = (colunas[6] != null)? ((Timestamp)colunas[6]).toLocalDateTime() : null;
		this.uf				         = (String) colunas[7];	
		this.cidade				     = (String) colunas[8];	
		this.bairro				     = (String) colunas[9];
		this.endereco			     = (String) colunas[10];
		this.telefone01			     = (String) colunas[11];
		this.telefone02			     = (String) colunas[12];
		this.celular			     = (String) colunas[13];
		this.descricaoEspecialidade = (String) colunas[14];
	}

	public Long getIdClinica() {
		return idClinica;
	}

	public void setIdClinica(Long idClinica) {
		this.idClinica = idClinica;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String razaoSocial) {
		this.nomeRazaoSocial = razaoSocial;
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getDescricaoEspecialidade() {
		return descricaoEspecialidade;
	}

	public void setDescricaoEspecialidade(String descricaoEspecialidade) {
		this.descricaoEspecialidade = descricaoEspecialidade;
	}
	
}
