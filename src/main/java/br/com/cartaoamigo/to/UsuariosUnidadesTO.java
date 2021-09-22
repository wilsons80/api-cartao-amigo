package br.com.cartaoamigo.to;

public class UsuariosUnidadesTO {

	private Long id;
	private UnidadeTO unidade;
	private UsuariosTO usuarioSistema;
	private Boolean principal;
	
	
	public UsuariosUnidadesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public UsuariosTO getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuariosTO usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
}
