package br.com.cartaoamigo.ws.pagarme.to;

import java.util.List;

public class NovaAssinaturaPlanoTO {

	private String plan_id;
	private String payment_method;
	private String customer_id;
	private String card_token;
    private Integer boleto_due_days;
    private String bank;
    private String idCartaoPagarMe;
    
	private String codigoCorretor;
	private String voucher;
	private Long   idPlano;
	private Long   idTitular;
	
	private List<DiscontoTO> discounts;
	
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

	public String getCodigoCorretor() {
		return codigoCorretor;
	}

	public void setCodigoCorretor(String codigoCorretor) {
		this.codigoCorretor = codigoCorretor;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}

	public Long getIdTitular() {
		return idTitular;
	}

	public void setIdTitular(Long idTitular) {
		this.idTitular = idTitular;
	}

	public List<DiscontoTO> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscontoTO> discounts) {
		this.discounts = discounts;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getIdCartaoPagarMe() {
		return idCartaoPagarMe;
	}

	public void setIdCartaoPagarMe(String idCartaoPagarMe) {
		this.idCartaoPagarMe = idCartaoPagarMe;
	}

	@Override
	public String toString() {
		return "NovaAssinaturaPlanoTO [plan_id=" + plan_id + ", payment_method=" + payment_method + ", customer_id="
				+ customer_id + ", card_token=" + card_token + ", boleto_due_days=" + boleto_due_days + ", bank=" + bank
				+ ", idCartaoPagarMe=" + idCartaoPagarMe + ", codigoCorretor=" + codigoCorretor + ", voucher=" + voucher
				+ ", idPlano=" + idPlano + ", idTitular=" + idTitular + ", discounts=" + discounts + "]";
	}


}
