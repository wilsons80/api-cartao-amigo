package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class UltimaCobrancaFaturaTO {

	private String id;
    private String transaction_type;
    private String gateway_id;    
    private Integer amount;
    private String status;
    private String success;    
    private Integer paid_amount;
    private LocalDateTime paid_at;
    private String url; //url
    private String pdf; //url
    private String line;
    private String barcode; //url
    private String qr_code; //url
    private String nosso_numero;
    private String bank;
    private String instructions;
    private LocalDateTime due_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

	public UltimaCobrancaFaturaTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(String gateway_id) {
		this.gateway_id = gateway_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(Integer paid_amount) {
		this.paid_amount = paid_amount;
	}

	public LocalDateTime getPaid_at() {
		return paid_at;
	}

	public void setPaid_at(LocalDateTime paid_at) {
		this.paid_at = paid_at;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	public String getNosso_numero() {
		return nosso_numero;
	}

	public void setNosso_numero(String nosso_numero) {
		this.nosso_numero = nosso_numero;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	

}
