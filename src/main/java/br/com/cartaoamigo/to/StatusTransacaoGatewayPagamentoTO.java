package br.com.cartaoamigo.to;

public class StatusTransacaoGatewayPagamentoTO {
	private Long id;
	private String descricao;
	private GatewayPagamentoTO gatewayPagamento;
	private Long codigoTransacao;
	
	public StatusTransacaoGatewayPagamentoTO() {
		
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

	public GatewayPagamentoTO getGatewayPagamento() {
		return gatewayPagamento;
	}

	public void setGatewayPagamento(GatewayPagamentoTO gatewaypagamento) {
		this.gatewayPagamento = gatewaypagamento;
	}

	public Long getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(Long codigotransacao) {
		this.codigoTransacao = codigotransacao;
	}
	
}

     
     
     
     