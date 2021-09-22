package br.com.cartaoamigo.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.cartaoamigo.infra.adapter.LocalDateTimeAdapter;

public class UsuariosTO {
	
	private Long id;
	private String username;
	private String senha;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataUltimoAcesso;
	
	private Long qtdAcessoNegado;
	private Boolean stAtivo;
	private Boolean stTrocaSenha;
	private PessoaFisicaTO pessoaFisica;
	private TipoUsuarioTO tipoUsuario;
	
	private List<PerfilAcessoUsuarioTO> gruposAcesso;
	
	public UsuariosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(LocalDateTime dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Long getQtdAcessoNegado() {
		return qtdAcessoNegado;
	}

	public void setQtdAcessoNegado(Long qtdAcessoNegado) {
		this.qtdAcessoNegado = qtdAcessoNegado;
	}

	public Boolean getStAtivo() {
		return stAtivo;
	}

	public void setStAtivo(Boolean stAtivo) {
		this.stAtivo = stAtivo;
	}

	public Boolean getStTrocaSenha() {
		return stTrocaSenha;
	}

	public void setStTrocaSenha(Boolean stTrocaSenha) {
		this.stTrocaSenha = stTrocaSenha;
	}

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public TipoUsuarioTO getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioTO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<PerfilAcessoUsuarioTO> getGruposAcesso() {
		return gruposAcesso;
	}

	public void setGruposAcesso(List<PerfilAcessoUsuarioTO> gruposAcesso) {
		this.gruposAcesso = gruposAcesso;
	}

}
