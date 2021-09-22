package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PrimaryReceiverTO {

	public String publicKey;
	
	public PrimaryReceiverTO() {
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}


}
