package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class DocumentTO {
	private List<String> type;
	private List<String> value;
	
	public DocumentTO() {
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}
	
}
