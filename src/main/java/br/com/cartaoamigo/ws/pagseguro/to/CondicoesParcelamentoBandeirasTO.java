package br.com.cartaoamigo.ws.pagseguro.to;

public class CondicoesParcelamentoBandeirasTO {

	private Boolean error;
	private ParcelasTO installments;
	
	public CondicoesParcelamentoBandeirasTO() {
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public ParcelasTO getInstallments() {
		return installments;
	}

	public void setInstallments(ParcelasTO installments) {
		this.installments = installments;
	}

	
}
