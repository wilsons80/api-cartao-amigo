package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authorizationRequest")
public class AutorizacaoXMLTO {

	private String code;

	public AutorizacaoXMLTO() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
