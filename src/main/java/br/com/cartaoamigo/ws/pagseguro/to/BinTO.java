package br.com.cartaoamigo.ws.pagseguro.to;

public class BinTO  {
	
	private String length;
	private CountryTO country;	
	private String bin;
	private BrandTO brand;	
	private String cvvSize;
	private String expirable;
	private String validationAlgorithm;
	private String bank;
	private String cardLevel;
	private String statusMessage;
	private String reasonMessage;
	
	public BinTO() {
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public CountryTO getCountry() {
		return country;
	}

	public void setCountry(CountryTO country) {
		this.country = country;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public BrandTO getBrand() {
		return brand;
	}

	public void setBrand(BrandTO brand) {
		this.brand = brand;
	}

	public String getCvvSize() {
		return cvvSize;
	}

	public void setCvvSize(String cvvSize) {
		this.cvvSize = cvvSize;
	}

	public String getExpirable() {
		return expirable;
	}

	public void setExpirable(String expirable) {
		this.expirable = expirable;
	}

	public String getValidationAlgorithm() {
		return validationAlgorithm;
	}

	public void setValidationAlgorithm(String validationAlgorithm) {
		this.validationAlgorithm = validationAlgorithm;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCardLevel() {
		return cardLevel;
	}

	public void setCardLevel(String cardLevel) {
		this.cardLevel = cardLevel;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getReasonMessage() {
		return reasonMessage;
	}

	public void setReasonMessage(String reasonMessage) {
		this.reasonMessage = reasonMessage;
	}

	
}
