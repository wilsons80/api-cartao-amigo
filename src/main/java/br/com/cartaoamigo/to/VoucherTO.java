package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class VoucherTO {
	
	private Long id;
	private String codigo;
	private String descricao;
	private Double porcentagem;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataValidade;
	private Boolean ativo;
	private Boolean utilizado;
	private LocalDateTime dataUtilizacao;
	private Long idPessoaFisica;
	
	public VoucherTO() {
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
