package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class PaymentMethodTO {
	
	private List<String> type;
	private List<String> code;
	
	public PaymentMethodTO() {
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public List<String> getCode() {
		return code;
	}
	public void setCode(List<String> code) {
		this.code = code;
	}
	
}
