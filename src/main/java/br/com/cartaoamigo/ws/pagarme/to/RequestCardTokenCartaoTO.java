package br.com.cartaoamigo.ws.pagarme.to;

public class RequestCardTokenCartaoTO {

	private String number;
	private String holder_name;
	private String exp_month;
	private String exp_year;
	private String cvv;
	private String label;

	public RequestCardTokenCartaoTO() {
	}
	
	public RequestCardTokenCartaoTO(String number, String holder_name, String exp_month, String exp_year, String cvv,String label) {
		super();
		this.number = number;
		this.holder_name = holder_name;
		this.exp_month = exp_month;
		this.exp_year = exp_year;
		this.cvv = cvv;
		this.label = label;
	}



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}

	public String getExp_month() {
		return exp_month;
	}

	public void setExp_month(String exp_month) {
		this.exp_month = exp_month;
	}

	public String getExp_year() {
		return exp_year;
	}

	public void setExp_year(String exp_year) {
		this.exp_year = exp_year;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
