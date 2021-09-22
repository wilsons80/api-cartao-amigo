package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class PhoneTO {
	private List<String> areaCode;
	private List<String> number;
	
	public PhoneTO() {
	}
	
	
	public List<String> getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(List<String> areaCode) {
		this.areaCode = areaCode;
	}
	public List<String> getNumber() {
		return number;
	}
	public void setNumber(List<String> number) {
		this.number = number;
	}
	
}
