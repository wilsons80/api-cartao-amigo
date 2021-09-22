package br.com.cartaoamigo.to;

public class CadastroAcessoTO {
	
	private Long   idUsuario;
	private Long   idGrupoAcesso;
	

	public CadastroAcessoTO() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdGrupoAcesso() {
		return idGrupoAcesso;
	}

	public void setIdGrupoAcesso(Long idGrupoAcesso) {
		this.idGrupoAcesso = idGrupoAcesso;
	}

}
