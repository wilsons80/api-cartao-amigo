package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CorretorDTO {

	private Long          id;
	private String        nome;
	private String        email;
	private String        cpf;
	private Boolean       ativo;
	private LocalDateTime dataCadastro;
	private String        codigoAutorizacao;
	private String        linkPagamento;	
	private String        token;

	
	public CorretorDTO() {
	}
	
	public CorretorDTO(Object[] colunas) {
		this.id                 = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome               = (String) colunas[1];
		this.email              = (String) colunas[2];
		this.cpf                = (String) colunas[3];
		this.ativo              = (colunas[4] != null) ? ((String)colunas[4]).toUpperCase().equals("S") : false;
		this.dataCadastro       = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime() : null;
		this.codigoAutorizacao  = (String) colunas[6];
		this.linkPagamento      = (String) colunas[7];
		this.token              = (String) colunas[8];
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
