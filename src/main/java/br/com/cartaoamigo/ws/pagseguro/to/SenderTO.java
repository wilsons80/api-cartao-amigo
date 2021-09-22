package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class SenderTO {
	private List<String> name;
	private List<String> email;
	private List<PhoneTO> phone;
	private List<DocumentsTO> documents;
	
	public SenderTO() {
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public List<PhoneTO> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneTO> phone) {
		this.phone = phone;
	}

	public List<DocumentsTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentsTO> documents) {
		this.documents = documents;
	}
	
	
}
