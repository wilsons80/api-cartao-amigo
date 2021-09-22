package br.com.cartaoamigo.to;

public class PerfilAmbienteSistemaTO {
	
	private Long id;	
	private String name;
	private Boolean fixedHeader;
	private Boolean fixedSidenav;
	private Boolean fixedSidenavUserContent;
	private Boolean fixedFooter;	
	private Boolean sidenavIsOpened;
	private Boolean sidenavIsPinned;
	private String menu;
	private String menuType;
	private String theme;
	private Boolean rtl;
	private Long idUsuario;
	
	public PerfilAmbienteSistemaTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getFixedHeader() {
		return fixedHeader;
	}

	public void setFixedHeader(Boolean fixedHeader) {
		this.fixedHeader = fixedHeader;
	}

	public Boolean getFixedSidenav() {
		return fixedSidenav;
	}

	public void setFixedSidenav(Boolean fixedSidenav) {
		this.fixedSidenav = fixedSidenav;
	}

	public Boolean getFixedSidenavUserContent() {
		return fixedSidenavUserContent;
	}

	public void setFixedSidenavUserContent(Boolean fixedSidenavUserContent) {
		this.fixedSidenavUserContent = fixedSidenavUserContent;
	}

	public Boolean getFixedFooter() {
		return fixedFooter;
	}

	public void setFixedFooter(Boolean fixedFooter) {
		this.fixedFooter = fixedFooter;
	}

	public Boolean getSidenavIsOpened() {
		return sidenavIsOpened;
	}

	public void setSidenavIsOpened(Boolean sidenavIsOpened) {
		this.sidenavIsOpened = sidenavIsOpened;
	}

	public Boolean getSidenavIsPinned() {
		return sidenavIsPinned;
	}

	public void setSidenavIsPinned(Boolean sidenavIsPinned) {
		this.sidenavIsPinned = sidenavIsPinned;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Boolean getRtl() {
		return rtl;
	}

	public void setRtl(Boolean rtl) {
		this.rtl = rtl;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
