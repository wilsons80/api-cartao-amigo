package br.com.cartaoamigo.to.pagarme;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.cartaoamigo.infra.adapter.LocalDateTimeAdapter;

public class NotificacaoFaturaTO {
	
	private String id;
	private String code;
	private Integer amount;
	private String status;          
	private String payment_method;
	private String subscriptionId;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime due_at;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime created_at;
	
	public NotificacaoFaturaTO() {
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

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
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

	@Override
	public String toString() {
		return "NotificacaoFaturaTO [id=" + id + ", code=" + code + ", amount=" + amount + ", status=" + status
				+ ", payment_method=" + payment_method + ", subscriptionId=" + subscriptionId + ", due_at=" + due_at
				+ ", created_at=" + created_at + "]";
	}

	
}
