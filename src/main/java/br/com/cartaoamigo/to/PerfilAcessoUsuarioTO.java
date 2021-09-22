package br.com.cartaoamigo.to;

public class PerfilAcessoUsuarioTO {

	private Long id;
	private Long idUsuario;
	private GrupoAcessoTO grupoAcesso;

	public PerfilAcessoUsuarioTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public GrupoAcessoTO getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(GrupoAcessoTO grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}

}
