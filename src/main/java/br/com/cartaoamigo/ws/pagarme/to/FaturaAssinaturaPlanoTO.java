package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class FaturaAssinaturaPlanoTO {

	private String id;
	private String code;
	private String url;
	private Integer amount;
	private Integer total_discount;
	private Integer total_increment;
	private String status;
	private String payment_method;
	private LocalDateTime due_at;
	private LocalDateTime created_at;
	private String subscriptionId;
	private CobrancaFaturaAssinaturaPlanoTO charge;
	private CicloAssinaturaTO cycle;

	public FaturaAssinaturaPlanoTO() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTotal_discount() {
		return total_discount;
	}

	public void setTotal_discount(Integer total_discount) {
		this.total_discount = total_discount;
	}

	public Integer getTotal_increment() {
		return total_increment;
	}

	public void setTotal_increment(Integer total_increment) {
		this.total_increment = total_increment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public LocalDateTime getDue_at() {
		return due_at;
	}

	public void setDue_at(LocalDateTime due_at) {
		this.due_at = due_at;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public CobrancaFaturaAssinaturaPlanoTO getCharge() {
		return charge;
	}

	public void setCharge(CobrancaFaturaAssinaturaPlanoTO charge) {
		this.charge = charge;
	}

	public CicloAssinaturaTO getCycle() {
		return cycle;
	}

	public void setCycle(CicloAssinaturaTO cycle) {
		this.cycle = cycle;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	
}
