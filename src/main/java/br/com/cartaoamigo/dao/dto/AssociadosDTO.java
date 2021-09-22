package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AssociadosDTO {
	
	private Long idTitular;
	private Long idPessoaFisica;
	private String nome;
	private Boolean ativo;
	private LocalDateTime dataCadastro;
	private String cpf;
	private String email;

	
	public AssociadosDTO() {
	}
	
	public AssociadosDTO(Object[] colunas) {
		this.idTitular          = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.idPessoaFisica     = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.nome               = (String) colunas[2];
		this.ativo              = (colunas[3] != null) ? ((String)colunas[3]).toUpperCase().equals("S") : false;
		this.dataCadastro       = (colunas[4] != null)? ((Timestamp)colunas[4]).toLocalDateTime() : null;
		this.cpf                = (String) colunas[5];
		this.email              = (String) colunas[6];
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idAssociado) {
		this.idTitular = idAssociado;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
