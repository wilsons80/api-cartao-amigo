package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class EnvioEmailTO {
	
	private Long           id;
	private Boolean        enviado;
	private LocalDateTime  dataCriacao;
	private LocalDateTime  dataEnvio;
	private PessoaFisicaTO pessoaFisica;
	private Long           idTipoEmail;
	private String         senhaProvisoria;
	private String         linkPagamento;
	private Long           idTipoPlano;
	private Boolean        isTitular;
	private String         username;
	private Long           idHistoricoPagamento;
	
	public EnvioEmailTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getIdTipoEmail() {
		return idTipoEmail;
	}

	public void setIdTipoEmail(Long idTipoEmail) {
		this.idTipoEmail = idTipoEmail;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getSenhaProvisoria() {
		return senhaProvisoria;
	}

	public void setSenhaProvisoria(String senhaProvisoria) {
		this.senhaProvisoria = senhaProvisoria;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

	public Long getIdTipoPlano() {
		return idTipoPlano;
	}

	public void setIdTipoPlano(Long idTipoPlano) {
		this.idTipoPlano = idTipoPlano;
	}

	public Boolean getIsTitular() {
		return isTitular;
	}

	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getIdHistoricoPagamento() {
		return idHistoricoPagamento;
	}

	public void setIdHistoricoPagamento(Long idHistoricoPagamento) {
		this.idHistoricoPagamento = idHistoricoPagamento;
	}

	
	
}
