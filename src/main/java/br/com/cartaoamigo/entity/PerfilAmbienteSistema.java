package br.com.cartaoamigo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;
import br.com.cartaoamigo.infra.dao.SimNaoConverter;

@Entity
@Table( name = "PerfilAmbienteSistema") 
public class PerfilAmbienteSistema implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_perfil_ambiente_sistema" )
	@SequenceGenerator(name = "sq_id_perfil_ambiente_sistema", sequenceName = "sq_id_perfil_ambiente_sistema", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_perfil_ambiente_sistema", unique = true, nullable = false, precision = 10)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "fixed_header")
	private Boolean fixedHeader;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "fixed_sidenav")
	private Boolean fixedSidenav;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "fixed_sidenav_user_content")
	private Boolean fixedSidenavUserContent;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "fixed_footer")
	private Boolean fixedFooter;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "sidenav_is_opened")
	private Boolean sidenavIsOpened;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "sidenav_is_pinned")
	private Boolean sidenavIsPinned;
	
	@Column(name = "menu")
	private String menu;
	
	@Column(name = "menu_type")
	private String menuType;
	
	@Column(name = "theme")
	private String theme;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "rtl")
	private Boolean rtl;
	
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	public PerfilAmbienteSistema() {
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


