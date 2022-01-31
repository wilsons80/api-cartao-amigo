package br.com.cartaoamigo.ws.pagarme.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditarCartaoAssinaturaPagarmeTO {

	private String card_id;
	private Long idTitular;
	private String idAsinaturaPagarMe;

	public EditarCartaoAssinaturaPagarmeTO() {
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public String getIdAsinaturaPagarMe() {
		return idAsinaturaPagarMe;
	}

	public void setIdAsinaturaPagarMe(String idAsinaturaPagarMe) {
		this.idAsinaturaPagarMe = idAsinaturaPagarMe;
	}


	
}
