
package br.com.cartaoamigo.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.cartaoamigo.infra.adapter.LocalDateTimeAdapter;

public class HistoricoPagamentoTO {
	
	private Long id;
	private TitularTO titular;
	private PessoaFisicaTO corretor;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dtPagamentoPlanoContratado;
	
	private GatewayPagamentoTO gatewayPagamento;
	private FormaPagamentoTO formaPagamento;
	private TipoPlanoTO tipoPlano;
	private Long qtdParcelas;
	private String numeroTransacaoGatewayPagamento;
	private StatusTransacaoGatewayPagamentoTO statusTransacao;

	private String tipoMetodoPagamento;
	private String linkPagamento;
	private String publicKeyPrimaryReceiver;
	private Long idVoucher;
	
	private Double valorPago;
	private Double valorCorretor;
	private CarteiraCartaoPagamentoAssociadoTO cartaoPagamento;
	
	public HistoricoPagamentoTO() {
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TitularTO getTitular() {
		return titular;
	}

	public void setTitular(TitularTO titular) {
		this.titular = titular;
	}

	public PessoaFisicaTO getCorretor() {
		return corretor;
	}

	public void setCorretor(PessoaFisicaTO corretor) {
		this.corretor = corretor;
	}

	public LocalDateTime getDtPagamentoPlanoContratado() {
		return dtPagamentoPlanoContratado;
	}

	public void setDtPagamentoPlanoContratado(LocalDateTime dtPagamentoPlanoContratado) {
		this.dtPagamentoPlanoContratado = dtPagamentoPlanoContratado;
	}

	public GatewayPagamentoTO getGatewayPagamento() {
		return gatewayPagamento;
	}


	public void setGatewayPagamento(GatewayPagamentoTO gatewayPagamento) {
		this.gatewayPagamento = gatewayPagamento;
	}


	public FormaPagamentoTO getFormaPagamento() {
		return formaPagamento;
	}


	public void setFormaPagamento(FormaPagamentoTO formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public TipoPlanoTO getTipoPlano() {
		return tipoPlano;
	}


	public void setTipoPlano(TipoPlanoTO tipoPlano) {
		this.tipoPlano = tipoPlano;
	}


	public Long getQtdParcelas() {
		return qtdParcelas;
	}


	public void setQtdParcelas(Long qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}


	public String getNumeroTransacaoGatewayPagamento() {
		return numeroTransacaoGatewayPagamento;
	}


	public void setNumeroTransacaoGatewayPagamento(String numeroTransacaoGatewayPagamento) {
		this.numeroTransacaoGatewayPagamento = numeroTransacaoGatewayPagamento;
	}


	public StatusTransacaoGatewayPagamentoTO getStatusTransacao() {
		return statusTransacao;
	}


	public void setStatusTransacao(StatusTransacaoGatewayPagamentoTO statusTransacao) {
		this.statusTransacao = statusTransacao;
	}

	public String getTipoMetodoPagamento() {
		return tipoMetodoPagamento;
	}

	public void setTipoMetodoPagamento(String tipoMetodoPagamento) {
		this.tipoMetodoPagamento = tipoMetodoPagamento;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

	public String getPublicKeyPrimaryReceiver() {
		return publicKeyPrimaryReceiver;
	}

	public void setPublicKeyPrimaryReceiver(String publicKeyPrimaryReceiver) {
		this.publicKeyPrimaryReceiver = publicKeyPrimaryReceiver;
	}

	public Long getIdVoucher() {
		return idVoucher;
	}

	public void setIdVoucher(Long idVoucher) {
		this.idVoucher = idVoucher;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getValorCorretor() {
		return valorCorretor;
	}

	public void setValorCorretor(Double valorCorretor) {
		this.valorCorretor = valorCorretor;
	}

	public CarteiraCartaoPagamentoAssociadoTO getCartaoPagamento() {
		return cartaoPagamento;
	}

	public void setCartaoPagamento(CarteiraCartaoPagamentoAssociadoTO cartaoPagamento) {
		this.cartaoPagamento = cartaoPagamento;
	}
	
	
}
