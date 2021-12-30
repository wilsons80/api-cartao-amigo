package br.com.cartaoamigo.ws.pagarme.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
public class TokenCartaoTO {

	private String id;

	public TokenCartaoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
