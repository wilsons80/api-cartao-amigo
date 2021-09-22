package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SenderSplitTO {
	
	private String name;
	private String email;
	private PhoneSplitTO phone;
	
	public SenderSplitTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PhoneSplitTO getPhone() {
		return phone;
	}

	public void setPhone(PhoneSplitTO phone) {
		this.phone = phone;
	}

	
	
}
