package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

public class CartaoTO {
	
	private Long id;
	private String numeroCartao;
	private String nomeImpresso;
	private String urlCode;	
	private String urlImagemCartao;
	private Boolean ativo;
	private ArquivosMetadadosTO metadados;
	private PessoaFisicaTO pessoaFisica;
	private Boolean isTitular;
	private Long idTitular;
	private LocalDateTime dataImpressao;
	private LocalDateTime dataCriado;
	private Boolean isPagamentoRealizado;
	private LocalDateTime dataValidadePlano;
	
	public CartaoTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getNomeImpresso() {
		return nomeImpresso;
	}
	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}
	public String getUrlImagemCartao() {
		return urlImagemCartao;
	}
	public void setUrlImagemCartao(String urlImagemCartao) {
		this.urlImagemCartao = urlImagemCartao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public ArquivosMetadadosTO getMetadados() {
		return metadados;
	}
	public void setMetadados(ArquivosMetadadosTO metadados) {
		this.metadados = metadados;
	}
	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public Boolean getIsTitular() {
		return isTitular;
	}
	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public LocalDateTime getDataImpressao() {
		return dataImpressao;
	}

	public void setDataImpressao(LocalDateTime dataImpressao) {
		this.dataImpressao = dataImpressao;
	}

	public LocalDateTime getDataCriado() {
		return dataCriado;
	}

	public void setDataCriado(LocalDateTime dataCriado) {
		this.dataCriado = dataCriado;
	}

	public Boolean getIsPagamentoRealizado() {
		return isPagamentoRealizado;
	}

	public void setIsPagamentoRealizado(Boolean isPagamentoRealizado) {
		this.isPagamentoRealizado = isPagamentoRealizado;
	}

	public LocalDateTime getDataValidadePlano() {
		return dataValidadePlano;
	}

	public void setDataValidadePlano(LocalDateTime dataValidadePlano) {
		this.dataValidadePlano = dataValidadePlano;
	}

	
}
