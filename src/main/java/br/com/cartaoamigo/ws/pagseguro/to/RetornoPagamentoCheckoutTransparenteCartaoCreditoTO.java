package br.com.cartaoamigo.ws.pagseguro.to;

public class RetornoPagamentoCheckoutTransparenteCartaoCreditoTO {
	
	private TransacaoTO transaction;
	
	public RetornoPagamentoCheckoutTransparenteCartaoCreditoTO() {
	}

	public TransacaoTO getTransaction() {
		return transaction;
	}

	public void setTransaction(TransacaoTO transaction) {
		this.transaction = transaction;
	}

	
}
