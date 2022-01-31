package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
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


@Entity
@Table(name = "historico_pagamento")
public class HistoricoPagamento  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_historico_pagamento")
	@SequenceGenerator(name = "sq_id_historico_pagamento", sequenceName = "sq_id_historico_pagamento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_historico_pagamento", unique = true, nullable = false, precision = 10)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_titular")
	private Titular titular;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_corretor")
	private PessoaFisica corretor;

	@Column(name = "dt_pagamento_plano_contratado")
	private LocalDateTime dtPagamentoPlanoContratado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gateway_pagamento")
	private GatewayPagamento gatewayPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_forma_pagamento")
	private FormaPagamento formaPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_plano")
	private TipoPlano tipoPlano;

	@Column(name = "qtd_parcelas")
	private Long qtdParcelas;
	
	@Column(name = "nr_transacao_gateway_pagamento")
	private String numeroTransacaoGatewayPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_status_transacao")
	private StatusTransacaoGatewayPagamento statusTransacao;
	
	@Column(name = "tp_metodo_pagamento")
	private String tipoMetodoPagamento;
	
	@Column(name = "link_pagamento")
	private String linkPagamento;

	@Column(name = "public_key_primary_receiver")
	private String publicKeyPrimaryReceiver;
	
	@Column(name = "id_voucher")
	private Long idVoucher;
	
	@Column(name = "VL_PAGO")
	private Double valorPago;
	
	@Column(name = "VL_CORRETOR")
	private Double valorCorretor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_carteira_cartao_pagamento_associado")
	private CarteiraCartaoPagamentoAssociado cartaoPagamento;
	
	
	public HistoricoPagamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public LocalDateTime getDtPagamentoPlanoContratado() {
		return dtPagamentoPlanoContratado;
	}

	public void setDtPagamentoPlanoContratado(LocalDateTime dtPagamentoPlanoContratado) {
		this.dtPagamentoPlanoContratado = dtPagamentoPlanoContratado;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public void setGatewayPagamento(GatewayPagamento gatewayPagamento) {
		this.gatewayPagamento = gatewayPagamento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public TipoPlano getTipoPlano() {
		return tipoPlano;
	}

	public void setTipoPlano(TipoPlano tipoPlano) {
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

	public StatusTransacaoGatewayPagamento getStatusTransacao() {
		return statusTransacao;
	}

	public void setStatusTransacao(StatusTransacaoGatewayPagamento statusTransacao) {
		this.statusTransacao = statusTransacao;
	}

	public PessoaFisica getCorretor() {
		return corretor;
	}

	public void setCorretor(PessoaFisica corretor) {
		this.corretor = corretor;
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

	public CarteiraCartaoPagamentoAssociado getCartaoPagamento() {
		return cartaoPagamento;
	}

	public void setCartaoPagamento(CarteiraCartaoPagamentoAssociado cartaoPagamento) {
		this.cartaoPagamento = cartaoPagamento;
	}

	@Override
	public String toString() {
		return "HistoricoPagamento [id=" + id + ", titular=" + titular + ", corretor=" + corretor
				+ ", dtPagamentoPlanoContratado=" + dtPagamentoPlanoContratado + ", gatewayPagamento="
				+ gatewayPagamento + ", formaPagamento=" + formaPagamento + ", tipoPlano=" + tipoPlano
				+ ", qtdParcelas=" + qtdParcelas + ", numeroTransacaoGatewayPagamento="
				+ numeroTransacaoGatewayPagamento + ", statusTransacao=" + statusTransacao + ", tipoMetodoPagamento="
				+ tipoMetodoPagamento + ", linkPagamento=" + linkPagamento + ", publicKeyPrimaryReceiver="
				+ publicKeyPrimaryReceiver + ", idVoucher=" + idVoucher + ", valorPago=" + valorPago
				+ ", valorCorretor=" + valorCorretor + ", cartaoPagamento=" + cartaoPagamento + "]";
	}
	
	
}