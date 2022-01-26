package br.com.cartaoamigo.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class VoucherDTO {

	private Long          idVoucher;
	private String        codigo;
	private String        descricaoPromocao;
	private Double        porcentagem;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataValidade;
	private Boolean       ativo;
	private Boolean       utilizado;
	private LocalDateTime dataUtilizacao;
	private String        nomePessoaUlilizacao;
	private Long          qtdMesesDesconto;
	
	public VoucherDTO() {
	}
	
	public VoucherDTO(Object[] colunas) {
		this.idVoucher             = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.codigo                = (String) colunas[1];
		this.descricaoPromocao     = (String) colunas[2];
		this.porcentagem           = (colunas[3] != null)? ((BigDecimal)colunas[3]).doubleValue() : null;
		this.dataCriacao           = (colunas[4] != null)? ((Timestamp)colunas[4]).toLocalDateTime() : null;
		this.dataValidade          = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime() : null;
		this.ativo                 = (colunas[6] != null) ? ((String)colunas[6]).toUpperCase().equals("S") : false;
		this.utilizado             = (colunas[7] != null) ? ((String)colunas[7]).toUpperCase().equals("S") : false;
		this.dataUtilizacao        = (colunas[8] != null)? ((Timestamp)colunas[8]).toLocalDateTime() : null;
		this.nomePessoaUlilizacao  = (String) colunas[9];
		this.qtdMesesDesconto      = (colunas[10] != null)? ((BigDecimal)colunas[10]).longValue() : null;
	}

	public Long getIdVoucher() {
		return idVoucher;
	}

	public void setIdVoucher(Long idVoucher) {
		this.idVoucher = idVoucher;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricaoPromocao() {
		return descricaoPromocao;
	}

	public void setDescricaoPromocao(String descricaoPromocao) {
		this.descricaoPromocao = descricaoPromocao;
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

	public String getNomePessoaUlilizacao() {
		return nomePessoaUlilizacao;
	}

	public void setNomePessoaUlilizacao(String nomePessoaUlilizacao) {
		this.nomePessoaUlilizacao = nomePessoaUlilizacao;
	}

	public LocalDateTime getDataUtilizacao() {
		return dataUtilizacao;
	}

	public void setDataUtilizacao(LocalDateTime dataUtilizacao) {
		this.dataUtilizacao = dataUtilizacao;
	}

	public Long getQtdMesesDesconto() {
		return qtdMesesDesconto;
	}

	public void setQtdMesesDesconto(Long qtdMesesDesconto) {
		this.qtdMesesDesconto = qtdMesesDesconto;
	}


}
