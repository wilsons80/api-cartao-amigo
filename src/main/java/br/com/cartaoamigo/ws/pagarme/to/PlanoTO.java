package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class PlanoTO {

	private String id;
	private String name;
	private String url;
	private String currency;
	private Long minimum_price;
	private String interval;
	private Integer interval_count;
	private String billing_type;
	private String status;
	private LocalDateTime created_at;

	public PlanoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Long getMinimum_price() {
		return minimum_price;
	}

	public void setMinimum_price(Long minimum_price) {
		this.minimum_price = minimum_price;
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

}
