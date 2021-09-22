package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

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
@Table(name = "VOUCHER")
public class Voucher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_voucher")
	@SequenceGenerator(name = "sq_id_voucher", sequenceName = "sq_id_voucher", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "ID_VOUCHER")
	private Long id;

	@Column(name = "CD_VOUCHER")
	private String codigo;

	@Column(name = "DS_PROMOCAO")
	private String descricao;
	
	@Column(name = "PORCENTAGEM_VOUCHER")
	private Double porcentagem;
	
	@Column(name = "DT_CRIACAO")
	private LocalDateTime dataCriacao;
	
	@Column(name = "DT_VALIDADE")
	private LocalDateTime dataValidade;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "ST_ATIVO")
	private Boolean ativo;

	@Convert(converter = SimNaoConverter.class)
	@Column(name = "ST_UTILIZADO")
	private Boolean utilizado;
	
	@Column(name = "DT_UTILIZACAO")
	private LocalDateTime dataUtilizacao;
	
	@Column(name = "ID_PESSOA_FISICA")
	private Long idPessoaFisica;
	
	
	public Voucher() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDateTime dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getUtilizado() {
		return utilizado;
	}

	public void setUtilizado(Boolean utilizado) {
		this.utilizado = utilizado;
	}

	public LocalDateTime getDataUtilizacao() {
		return dataUtilizacao;
	}

	public void setDataUtilizacao(LocalDateTime dataUtilizacao) {
		this.dataUtilizacao = dataUtilizacao;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

}
