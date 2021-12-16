package br.com.cartaoamigo.to.pagarme;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.cartaoamigo.infra.adapter.LocalDateTimeAdapter;

public class NotificacaoUltimaTransacaoTO {
	
	private String operation_key;
	private String id;
	private String transaction_type;
	private String gateway_id; 
	private Integer amount;
	private String status;
	private Boolean success;
	private Integer installments;
	private String acquirer_name;
    private String acquirer_tid;
    private String acquirer_nsu;
    private String acquirer_auth_code;
    private String acquirer_message;
    private String acquirer_return_code;
    private String operation_type;
    
    private NotificacaoCartaoTO card;
    
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime created_at;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime updated_at;
	
	private NotificacaoGatewayRepostaTO gateway_response;
	
	public NotificacaoUltimaTransacaoTO() {
	}

	public String getOperation_key() {
		return operation_key;
	}

	public void setOperation_key(String operation_key) {
		this.operation_key = operation_key;
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

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public String getAcquirer_name() {
		return acquirer_name;
	}

	public void setAcquirer_name(String acquirer_name) {
		this.acquirer_name = acquirer_name;
	}

	public String getAcquirer_tid() {
		return acquirer_tid;
	}

	public void setAcquirer_tid(String acquirer_tid) {
		this.acquirer_tid = acquirer_tid;
	}

	public String getAcquirer_nsu() {
		return acquirer_nsu;
	}

	public void setAcquirer_nsu(String acquirer_nsu) {
		this.acquirer_nsu = acquirer_nsu;
	}

	public String getAcquirer_auth_code() {
		return acquirer_auth_code;
	}

	public void setAcquirer_auth_code(String acquirer_auth_code) {
		this.acquirer_auth_code = acquirer_auth_code;
	}

	public String getAcquirer_message() {
		return acquirer_message;
	}

	public void setAcquirer_message(String acquirer_message) {
		this.acquirer_message = acquirer_message;
	}

	public String getAcquirer_return_code() {
		return acquirer_return_code;
	}

	public void setAcquirer_return_code(String acquirer_return_code) {
		this.acquirer_return_code = acquirer_return_code;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public NotificacaoCartaoTO getCard() {
		return card;
	}

	public void setCard(NotificacaoCartaoTO card) {
		this.card = card;
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

	public NotificacaoGatewayRepostaTO getGateway_response() {
		return gateway_response;
	}

	public void setGateway_response(NotificacaoGatewayRepostaTO gateway_response) {
		this.gateway_response = gateway_response;
	}

	@Override
	public String toString() {
		return "NotificacaoUltimaTransacaoTO [operation_key=" + operation_key + ", id=" + id + ", transaction_type="
				+ transaction_type + ", gateway_id=" + gateway_id + ", amount=" + amount + ", status=" + status
				+ ", success=" + success + ", installments=" + installments + ", acquirer_name=" + acquirer_name
				+ ", acquirer_tid=" + acquirer_tid + ", acquirer_nsu=" + acquirer_nsu + ", acquirer_auth_code="
				+ acquirer_auth_code + ", acquirer_message=" + acquirer_message + ", acquirer_return_code="
				+ acquirer_return_code + ", operation_type=" + operation_type + ", card=" + card + ", created_at="
				+ created_at + ", updated_at=" + updated_at + ", gateway_response=" + gateway_response + "]";
	}


	
}
