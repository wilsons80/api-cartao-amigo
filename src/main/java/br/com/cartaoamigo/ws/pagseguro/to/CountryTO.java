package br.com.cartaoamigo.ws.pagseguro.to;

public class CountryTO  {
	
	private String name;
	private String id;
	private String isoCode;
	private String isoCodeThreeDigits;

	public CountryTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getIsoCodeThreeDigits() {
		return isoCodeThreeDigits;
	}

	public void setIsoCodeThreeDigits(String isoCodeThreeDigits) {
		this.isoCodeThreeDigits = isoCodeThreeDigits;
	}

	
}
