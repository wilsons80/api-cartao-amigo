package br.com.cartaoamigo.to;

public class EmailTO {
	
	private Long           id;
	private PessoaFisicaTO pessoaFisica;
	private Long           idTipoEmail;
	private String         linkPagamento;
	private String         linkRedefinicaoSenha;
	private String         username;
	private String         senhaProvisoria;
	private Long           idTipoPlano;
	private Boolean        isTitular;
	private Long           idHistoricoPagamento;
	
	public EmailTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

	public String getLinkRedefinicaoSenha() {
		return linkRedefinicaoSenha;
	}

	public void setLinkRedefinicaoSenha(String linkRedefinicaoSenha) {
		this.linkRedefinicaoSenha = linkRedefinicaoSenha;
	}

	public String getSenhaProvisoria() {
		return senhaProvisoria;
	}

	public void setSenhaProvisoria(String senhaProvisoria) {
		this.senhaProvisoria = senhaProvisoria;
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

	public Long getIdHistoricoPagamento() {
		return idHistoricoPagamento;
	}

	public void setIdHistoricoPagamento(Long idHistoricoPagamento) {
		this.idHistoricoPagamento = idHistoricoPagamento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
