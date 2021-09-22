package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
public class TokenCartaoTO {

	private String token;

	public TokenCartaoTO() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
