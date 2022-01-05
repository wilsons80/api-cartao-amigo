package br.com.cartaoamigo.to.pagarme;

public class NotificacaoTransacaoTO {
	
	private String id;
	private String code;
	private String status;          // Valores possíveis: pending, paid, canceled, scheduled ou failed.
	
	private NotificacaoFaturaTO invoice;
	
	public NotificacaoTransacaoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public NotificacaoFaturaTO getInvoice() {
		return invoice;
	}

	public void setInvoice(NotificacaoFaturaTO invoice) {
		this.invoice = invoice;
	}


}
