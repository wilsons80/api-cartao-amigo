package br.com.cartaoamigo.ws.pagarme.to;

public class CancelarAssinaturaPlanoTO {

	private Boolean cancel_pending_invoices;
	
	public CancelarAssinaturaPlanoTO() {
	}

	public Boolean getCancel_pending_invoices() {
		return cancel_pending_invoices;
	}

	public void setCancel_pending_invoices(Boolean cancel_pending_invoices) {
		this.cancel_pending_invoices = cancel_pending_invoices;
	}


}
