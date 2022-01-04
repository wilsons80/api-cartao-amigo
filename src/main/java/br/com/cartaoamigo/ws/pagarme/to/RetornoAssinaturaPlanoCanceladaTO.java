package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class RetornoAssinaturaPlanoCanceladaTO {

	private String id;
	private String code;
	private LocalDateTime start_at;
	private String interval;
	private Integer interval_count;
	private String billing_type;
	private Integer boleto_due_days;
	private LocalDateTime next_billing_at;
	private String payment_method;
	private String currency;
	private Integer installments;
	private String status;
	private LocalDateTime created_at;
	private ClientePagarMeTO customer;
	private PlanoTO plan;
	private CicloAssinaturaTO current_cycle;

	public RetornoAssinaturaPlanoCanceladaTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getStart_at() {
		return start_at;
	}

	public void setStart_at(LocalDateTime start_at) {
		this.start_at = start_at;
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

	public String getBilling_type() {
		return billing_type;
	}

	public void setBilling_type(String billing_type) {
		this.billing_type = billing_type;
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

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
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

	public ClientePagarMeTO getCustomer() {
		return customer;
	}

	public void setCustomer(ClientePagarMeTO customer) {
		this.customer = customer;
	}

	public PlanoTO getPlan() {
		return plan;
	}

	public void setPlan(PlanoTO plan) {
		this.plan = plan;
	}

	public CicloAssinaturaTO getCurrent_cycle() {
		return current_cycle;
	}

	public void setCurrent_cycle(CicloAssinaturaTO current_cycle) {
		this.current_cycle = current_cycle;
	}

}
