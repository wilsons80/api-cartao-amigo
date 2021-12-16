package br.com.cartaoamigo.to.pagarme;

public class NotificacaoClienteTO {
	
	private String id;
	private String name;
	private String email;          
	private String code;
	private String document;
	private String document_type;
	
	public NotificacaoClienteTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	@Override
	public String toString() {
		return "NotificacaoClienteTO [id=" + id + ", name=" + name + ", email=" + email + ", code=" + code
				+ ", document=" + document + ", document_type=" + document_type + "]";
	}

	
}
