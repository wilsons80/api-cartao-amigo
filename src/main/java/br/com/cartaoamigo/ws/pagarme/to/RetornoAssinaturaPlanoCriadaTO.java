package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class RetornoAssinaturaPlanoCriadaTO {

	private String id;
	private String payment_method;
	private String currency;
	private String interval;
	private Integer interval_count;
	private Integer boleto_due_days;
	private LocalDateTime next_billing_at;
	private Integer installments;
	private ClientePagarMeTO customer;
	private CartaoClienteTO card;
	private String status;
	private LocalDateTime created_at;
	private PlanoTO plan;
	
	public RetornoAssinaturaPlanoCriadaTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public Integer getInterval_count() {
		return interval_count;
	}

	public void setInterval_count(Integer interval_count) {
		this.interval_count = interval_count;
	}

	public Integer getBoleto_due_days() {
		return boleto_due_days;
	}

	public void setBoleto_due_days(Integer boleto_due_days) {
		this.boleto_due_days = boleto_due_days;
	}

	public LocalDateTime getNext_billing_at() {
		return next_billing_at;
	}

	public void setNext_billing_at(LocalDateTime next_billing_at) {
		this.next_billing_at = next_billing_at;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public ClientePagarMeTO getCustomer() {
		return customer;
	}

	public void setCustomer(ClientePagarMeTO customer) {
		this.customer = customer;
	}

	public CartaoClienteTO getCard() {
		return card;
	}

	public void setCard(CartaoClienteTO card) {
		this.card = card;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public PlanoTO getPlan() {
		return plan;
	}

	public void setPlan(PlanoTO plan) {
		this.plan = plan;
	}

}
