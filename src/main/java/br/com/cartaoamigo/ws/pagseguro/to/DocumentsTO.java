package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class DocumentsTO {
	private List<DocumentTO> document;
	
	public DocumentsTO() {
	}

	public List<DocumentTO> getDocument() {
		return document;
	}

	public void setDocument(List<DocumentTO> document) {
		this.document = document;
	}
	
	
}
