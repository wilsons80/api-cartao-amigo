package br.com.cartaoamigo.to;

public class ModuloTO {

	private Long id;
	private String descricao;
	private String nome;
	private ModuloTO moduloPai;
	private Boolean agrupador;
	private String routerLink;
	private String href;
	private String icone;
	private String target;
	private Boolean visivel;
	
	public ModuloTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ModuloTO getModuloPai() {
		return moduloPai;
	}

	public void setModuloPai(ModuloTO moduloPai) {
		this.moduloPai = moduloPai;
	}

	public Boolean getAgrupador() {
		return agrupador;
	}

	public void setAgrupador(Boolean agrupador) {
		this.agrupador = agrupador;
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
