package br.com.cartaoamigo.to.pagarme;

public class NotificacaoCartaoTO {
	
	private String id;
	private String first_six_digits;
	private String last_four_digits;          
	private String brand;
	private String holder_name;
	private Integer exp_month;
	private Integer exp_year;
	private String status;
	
	public NotificacaoCartaoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NotificacaoCartaoTO [id=" + id + ", first_six_digits=" + first_six_digits + ", last_four_digits="
				+ last_four_digits + ", brand=" + brand + ", holder_name=" + holder_name + ", exp_month=" + exp_month
				+ ", exp_year=" + exp_year + ", status=" + status + "]";
	}


	
}
