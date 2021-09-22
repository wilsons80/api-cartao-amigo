package br.com.cartaoamigo.to;

import java.util.List;

public class GrupoAcessoTO {
	
	private Long id;
	private String nomeGrupoAcesso;
	private PerfilAcessoTO perfilAcesso;
	
	private List<GrupoAcessoModulosTO> gruposAcessoModulos;
	
	public GrupoAcessoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeGrupoAcesso() {
		return nomeGrupoAcesso;
	}

	public void setNomeGrupoAcesso(String nome) {
		this.nomeGrupoAcesso = nome;
	}

	public PerfilAcessoTO getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcessoTO perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public List<GrupoAcessoModulosTO> getGruposAcessoModulos() {
		return gruposAcessoModulos;
	}

	public void setGruposAcessoModulos(List<GrupoAcessoModulosTO> gruposAcessoModulos) {
		this.gruposAcessoModulos = gruposAcessoModulos;
	}

	
}
