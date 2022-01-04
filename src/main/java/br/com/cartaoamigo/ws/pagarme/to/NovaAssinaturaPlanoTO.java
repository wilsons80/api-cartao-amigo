package br.com.cartaoamigo.ws.pagarme.to;

public class NovaAssinaturaPlanoTO {

	private String plan_id;
	private String payment_method;
	private String customer_id;
	private String card_token;
    private Integer boleto_due_days;

	public NovaAssinaturaPlanoTO() {
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCard_token() {
		return card_token;
	}

	public void setCard_token(String card_token) {
		this.card_token = card_token;
	}

	public Integer getBoleto_due_days() {
		return boleto_due_days;
	}

	public void setBoleto_due_days(Integer boleto_due_days) {
		this.boleto_due_days = boleto_due_days;
	}

	

}
