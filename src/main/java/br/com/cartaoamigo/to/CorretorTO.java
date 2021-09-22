package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class CorretorTO {
	private Long id;
	private String publicKey;
	private String codigo;
	private PessoaFisicaTO pessoaFisica;
	private Boolean ativo;
	private LocalDateTime dtCadastro;
	private Boolean isPorcentagem;
	private Double valorRecebimento;
	private String linkPagamento;	
	private String token;
	
	public CorretorTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getIsPorcentagem() {
		return isPorcentagem;
	}

	public void setIsPorcentagem(Boolean isPorcentagem) {
		this.isPorcentagem = isPorcentagem;
	}

	public Double getValorRecebimento() {
		return valorRecebimento;
	}

	public void setValorRecebimento(Double valorRecebimento) {
		this.valorRecebimento = valorRecebimento;
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
