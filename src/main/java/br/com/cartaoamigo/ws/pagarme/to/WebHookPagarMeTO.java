package br.com.cartaoamigo.ws.pagarme.to;

import br.com.cartaoamigo.to.pagarme.NotificacaoTransacaoTO;

public class WebHookPagarMeTO {

	private String id;
	private String event;
	private String status;
	private String attempts;
	private NotificacaoTransacaoTO data;
	
	public WebHookPagarMeTO() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
}
