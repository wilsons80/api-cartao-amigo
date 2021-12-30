package br.com.cartaoamigo.ws.pagarme.to;

public class RequestTokenCartaoTO {

	private String type;
	private RequestCardTokenCartaoTO card;

	public RequestTokenCartaoTO() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RequestCardTokenCartaoTO getCard() {
		return card;
	}

	public void setCard(RequestCardTokenCartaoTO card) {
		this.card = card;
	}
	

}
