package br.com.cartaoamigo.to;

public class GrupoAcessoModulosTO {
	
	private Long id;
	private Long idGrupoAcesso;
	private ModuloTO modulo;
	
	public GrupoAcessoModulosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idGrupoAcessoModulo) {
		this.id = idGrupoAcessoModulo;
	}

	public Long getIdGrupoAcesso() {
		return idGrupoAcesso;
	}

	public void setIdGrupoAcesso(Long idGrupoAcesso) {
		this.idGrupoAcesso = idGrupoAcesso;
	}

	public ModuloTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloTO modulo) {
		this.modulo = modulo;
	}
	

}
