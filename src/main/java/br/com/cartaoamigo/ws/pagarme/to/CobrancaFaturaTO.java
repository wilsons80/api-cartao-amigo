package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class CobrancaFaturaTO {

	private String id;
    private String code;
    private Integer amount;
    private Integer paid_amount;
    private String status;
    private String currency;
    private String payment_method;
    private LocalDateTime due_at;
    private LocalDateTime paid_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    
    private FaturaAssinaturaPlanoTO invoice;
    private UltimaCobrancaFaturaTO last_transaction;

	public CobrancaFaturaTO() {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(Integer paid_amount) {
		this.paid_amount = paid_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public LocalDateTime getPaid_at() {
		return paid_at;
	}

	public void setPaid_at(LocalDateTime paid_at) {
		this.paid_at = paid_at;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public UltimaCobrancaFaturaTO getLast_transaction() {
		return last_transaction;
	}

	public void setLast_transaction(UltimaCobrancaFaturaTO last_transaction) {
		this.last_transaction = last_transaction;
	}

	public FaturaAssinaturaPlanoTO getInvoice() {
		return invoice;
	}

	public void setInvoice(FaturaAssinaturaPlanoTO invoice) {
		this.invoice = invoice;
	}


}
