package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class UsuarioDTO {
	
	private Long              idUsuario;
	private Boolean           ativo ;   
	private String            nome;
	private String            cpf;
	private String            email;
	private LocalDateTime     dataCadastro;
	private String            descTipoUsuario;
	private Long              idTipoUsuario;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Object[] colunas) {
		this.idUsuario          = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.ativo              = (colunas[1] != null) ? ((String)colunas[1]).toUpperCase().equals("S") : false;
		this.nome               = (String)colunas[2];
		this.cpf                = (String)colunas[3];
		this.email              = (String)colunas[4];
		this.dataCadastro       = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime() : null;
		this.descTipoUsuario    = (String)colunas[6];
		this.idTipoUsuario      = (colunas[7] != null)? ((BigDecimal)colunas[7]).longValue() : null;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDescTipoUsuario() {
		return descTipoUsuario;
	}

	public void setDescTipoUsuario(String descTipoUsuario) {
		this.descTipoUsuario = descTipoUsuario;
	}

	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	

}