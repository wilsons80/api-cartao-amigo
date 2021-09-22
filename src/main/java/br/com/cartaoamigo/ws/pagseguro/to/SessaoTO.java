package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "session")
public class SessaoTO {

	private String id;

	public SessaoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
