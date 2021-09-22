package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class DependentesTitularTO {
	
	private Long id;
	private PessoaFisicaTO pessoaFisica;
	private Long idTitular;
	private Boolean ativo;
	private LocalDateTime dtCadastro;
	private CartaoTO cartao;
	private Boolean exclusaoLogica;
	
	
	public DependentesTitularTO(){
		
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

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
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

	public CartaoTO getCartao() {
		return cartao;
	}

	public void setCartao(CartaoTO cartao) {
		this.cartao = cartao;
	}

	public Boolean getExclusaoLogica() {
		return exclusaoLogica;
	}

	public void setExclusaoLogica(Boolean exclusaoLogica) {
		this.exclusaoLogica = exclusaoLogica;
	}
	

}
