package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class PerfilAcessoUsuarioDTO {
	
	private Long idUsuario;
	private String nomeUsuario;
	private Long idModulo;
	private String nomeModulo;
	private Long idGrupoAcesso;
	private String descricaoGrupoAcesso;
	private Long idPerfilAcesso;
	
	public PerfilAcessoUsuarioDTO() {
	}
	
	public PerfilAcessoUsuarioDTO(Object[] colunas) {
		this.idUsuario            = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nomeUsuario          = (String) colunas[1];
		this.idModulo             = (colunas[2] != null)? ((BigDecimal)colunas[2]).longValue() : null;
		this.nomeModulo           = (String) colunas[3];
		this.idGrupoAcesso        = (colunas[4] != null)? ((BigDecimal)colunas[4]).longValue() : null;
		this.descricaoGrupoAcesso = (String) colunas[5];
		this.idPerfilAcesso       = (colunas[6] != null)? ((BigDecimal)colunas[6]).longValue() : null;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNomeModulo() {
		return nomeModulo;
	}

	public void setNomeModulo(String nomeModulo) {
		this.nomeModulo = nomeModulo;
	}

	public Long getIdGrupoAcesso() {
		return idGrupoAcesso;
	}

	public void setIdGrupoAcesso(Long idGrupoAcesso) {
		this.idGrupoAcesso = idGrupoAcesso;
	}

	public String getDescricaoGrupoAcesso() {
		return descricaoGrupoAcesso;
	}

	public void setDescricaoGrupoAcesso(String descricaoGrupoAcesso) {
		this.descricaoGrupoAcesso = descricaoGrupoAcesso;
	}

	public Long getIdPerfilAcesso() {
		return idPerfilAcesso;
	}

	public void setIdPerfilAcesso(Long idPerfilAcesso) {
		this.idPerfilAcesso = idPerfilAcesso;
	}


}
