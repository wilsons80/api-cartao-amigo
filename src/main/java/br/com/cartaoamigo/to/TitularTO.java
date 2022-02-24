package br.com.cartaoamigo.to;

import java.time.LocalDateTime;
import java.util.List;

public class TitularTO {

	private Long id;
	private PessoaFisicaTO pessoaFisica;
	private Boolean ativo;
	private List<DependentesTitularTO> dependentes;
	private LocalDateTime dtCadastro;
	private String codigoCorretor;
	
	private String senha;
	private String senhaConfirmada;
	private CartaoTO cartao;

	public TitularTO() {
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

	public List<DependentesTitularTO> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<DependentesTitularTO> dependentes) {
		this.dependentes = dependentes;
	}
	
	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}

	public String getCodigoCorretor() {
		return codigoCorretor;
	}

	public void setCodigoCorretor(String codigoCorretor) {
		this.codigoCorretor = codigoCorretor;
	}

	public CartaoTO getCartao() {
		return cartao;
	}

	public void setCartao(CartaoTO cartao) {
		this.cartao = cartao;
	}

}
