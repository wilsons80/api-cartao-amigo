package br.com.cartaoamigo.to;

public class StatusTransacaoGatewayPagamentoTO {
	private Long id;
	private String descricao;
	private GatewayPagamentoTO gatewayPagamento;
	private String codigoTransacao;
	
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

	public String getCodigoTransacao() {
		return codigoTransacao;
	}

	public void setCodigoTransacao(String codigotransacao) {
		this.codigoTransacao = codigotransacao;
	}

	@Override
	public String toString() {
		return "StatusTransacaoGatewayPagamentoTO [id=" + id + ", descricao=" + descricao + ", gatewayPagamento="
				+ gatewayPagamento + ", codigoTransacao=" + codigoTransacao + "]";
	}
	
	
}

     
     
     
     