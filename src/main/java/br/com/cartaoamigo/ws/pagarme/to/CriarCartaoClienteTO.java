package br.com.cartaoamigo.ws.pagarme.to;

public class CriarCartaoClienteTO {

	private String id;
	private String number;
	private String first_six_digits;
	private String last_four_digits;
	private String holder_name;
	private String holder_document;
	private Integer exp_month;
	private Integer exp_year;
	private String cvv;
	private String brand;
	private String label; // "Sua bandeira"
	private String status;
	
	private ClientePagarMeTO customer;
	
	private EnderecoCobrancaCartaoClientePagarMeTO billing_address;

	public CriarCartaoClienteTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFirst_six_digits() {
		return first_six_digits;
	}

	public void setFirst_six_digits(String first_six_digits) {
		this.first_six_digits = first_six_digits;
	}

	public String getLast_four_digits() {
		return last_four_digits;
	}

	public void setLast_four_digits(String last_four_digits) {
		this.last_four_digits = last_four_digits;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}

	public String getHolder_document() {
		return holder_document;
	}

	public void setHolder_document(String holder_document) {
		this.holder_document = holder_document;
	}

	public Integer getExp_month() {
		return exp_month;
	}

	public void setExp_month(Integer exp_month) {
		this.exp_month = exp_month;
	}

	public Integer getExp_year() {
		return exp_year;
	}

	public void setExp_year(Integer exp_year) {
		this.exp_year = exp_year;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EnderecoCobrancaCartaoClientePagarMeTO getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(EnderecoCobrancaCartaoClientePagarMeTO billing_address) {
		this.billing_address = billing_address;
	}

	public ClientePagarMeTO getCustomer() {
		return customer;
	}

	public void setCustomer(ClientePagarMeTO customer) {
		this.customer = customer;
	}

	

}
