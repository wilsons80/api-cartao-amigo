package br.com.cartaoamigo.to;

import java.util.List;

public class UsuarioLogadoTO {

	private Long    idUsuario;
	private String  username;
	private String  nomeUsuario;
	private String  email;
	private Boolean trocarSenha;
	private String  token;
	private Long    idPessoaFisica;
	private TipoUsuarioTO tipoUsuario;

	private List<AcessoModuloTO> modulos;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long id) {
		this.idUsuario = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public List<AcessoModuloTO> getModulos() {
		return modulos;
	}

	public void setModulos(List<AcessoModuloTO> unidades) {
		this.modulos = unidades;
	}

	public Boolean getTrocarSenha() {
		return trocarSenha;
	}

	public void setTrocarSenha(Boolean trocarSenha) {
		this.trocarSenha = trocarSenha;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuarioTO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioTO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
}
