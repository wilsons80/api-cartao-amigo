package br.com.cartaoamigo.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;
import br.com.cartaoamigo.infra.dao.SimNaoConverter;


/**
 * The persistent class for the modulos database table.
 * 
 */
@Entity
@Table(name="modulos")
public class Modulo  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_modulo")
	@SequenceGenerator(name = "sq_id_modulo", sequenceName = "sq_id_modulo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_modulo")
	private Long id;

	@Column(name="descricao")
	private String descricao;

	@Column(name="nome")
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="modulo_pai")
	private Modulo moduloPai;
	
	@Column(name="agrupador")
	@Convert(converter = SimNaoConverter.class)
	private Boolean agrupador;

	@Column(name="routerlink")
	private String routerLink;
	
	@Column(name="href")
	private String href;
	
	@Column(name="icon")
	private String icone;
	
	@Column(name="target")
	private String target;

	@Column(name="visivel")
	@Convert(converter = SimNaoConverter.class)
	private Boolean visivel;
	
	
	public Modulo() {
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

	public Modulo getModuloPai() {
		return moduloPai;
	}

	public void setModuloPai(Modulo moduloPai) {
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