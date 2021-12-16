package br.com.cartaoamigo.to.pagarme;

public class NotificacaoPagarmeTransacaoTO {

	private String id;
	private String event;
	private String attempts;
	private NotificacaoTransacaoTO data;

	public NotificacaoPagarmeTransacaoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	

	public String getAttempts() {
		return attempts;
	}

	public void setAttempts(String attempts) {
		this.attempts = attempts;
	}

	public NotificacaoTransacaoTO getData() {
		return data;
	}

	public void setData(NotificacaoTransacaoTO data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "NotificacaoPagarmeTransacaoTO [id=" + id + ", event=" + event + ", data=" + data + "]";
	}
	
	

}
