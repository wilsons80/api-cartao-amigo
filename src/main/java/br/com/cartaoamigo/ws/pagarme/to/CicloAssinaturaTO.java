package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class CicloAssinaturaTO {

    private String id;
    private LocalDateTime start_at;
    private LocalDateTime end_at;
    private LocalDateTime billing_at;
    private String status;
    private Integer cycle;

	public CicloAssinaturaTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getStart_at() {
		return start_at;
	}

	public void setStart_at(LocalDateTime start_at) {
		this.start_at = start_at;
	}

	public LocalDateTime getEnd_at() {
		return end_at;
	}

	public void setEnd_at(LocalDateTime end_at) {
		this.end_at = end_at;
	}

	public LocalDateTime getBilling_at() {
		return billing_at;
	}

	public void setBilling_at(LocalDateTime billing_at) {
		this.billing_at = billing_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	

}
