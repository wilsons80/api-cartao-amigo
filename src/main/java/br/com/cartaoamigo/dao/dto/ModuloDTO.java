package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;

public class ModuloDTO {

	private Long idModulo;
	private String descricao;
	private String nome;
	private String agrupador;
	private Long moduloPai;
	private String routerLink;
	private String href;
	private String icone;
	private String target;
	private Boolean visivel;
	
	public ModuloDTO() {
	}
	
	public ModuloDTO(Object[] colunas) {
		this.idModulo      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.descricao     = (String) colunas[1];
		this.nome          = (String) colunas[2];
		this.agrupador     = (String) colunas[3];
		this.moduloPai     = (colunas[4] != null)? ((BigDecimal)colunas[4]).longValue() : null;
		this.routerLink    = (String) colunas[5];
		this.href          = (String) colunas[6];       
		this.icone         = (String) colunas[7];      
		this.target        = (String) colunas[8];
		this.visivel       = (colunas[9] != null) ? ((String)colunas[9]).toUpperCase().equals("S") : false;
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(String agrupador) {
		this.agrupador = agrupador;
	}

	public Long getModuloPai() {
		return moduloPai;
	}

	public void setModuloPai(Long moduloPai) {
		this.moduloPai = moduloPai;
	}

	public String getRouterLink() {
		return routerLink;
	}

	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getVisivel() {
		return visivel;
	}

	public void setVisivel(Boolean visivel) {
		this.visivel = visivel;
	}

	
}
