package br.com.cartaoamigo.to;

import java.time.LocalDate;

public class ValidacaoCarteirinhaAssociadoTO {
	
	private String  numeroCartao;
	private String  nomeImpresso;
	private Boolean status;
	private Boolean stTitular;
	private TipoPlanoTO tipoPlano;
	private LocalDate dataFimValidade;
	
	public ValidacaoCarteirinhaAssociadoTO() {
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getStTitular() {
		return stTitular;
	}

	public void setStTitular(Boolean stTitular) {
		this.stTitular = stTitular;
	}

	public TipoPlanoTO getTipoPlano() {
		return tipoPlano;
	}

	public void setTipoPlano(TipoPlanoTO tipoPlano) {
		this.tipoPlano = tipoPlano;
	}

	public LocalDate getDataFimValidade() {
		return dataFimValidade;
	}

	public void setDataFimValidade(LocalDate dataFimValidade) {
		this.dataFimValidade = dataFimValidade;
	}

}
