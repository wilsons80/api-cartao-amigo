package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransacaoSplitTO {

	private Date date;
	private String code;
	private String reference;
	private String type;
	private	String status;
	private Date lastEventDate;
	private String linkPagamento;
	private PaymentMethodSplitTO paymentMethod;
	private Double grossAmount;
	private Double discountAmount;
	public CreditorFeesSplitTO creditorFees;
	private Double netAmount;
	private Double extraAmount;
	private Long installmentCount;
	private Long itemCount;
	private ItemsSplitTO  items;
	private SenderSplitTO sender;
	private PrimaryReceiverTO primaryReceiver;
	
	public TransacaoSplitTO() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastEventDate() {
		return lastEventDate;
	}

	public void setLastEventDate(Date lastEventDate) {
		this.lastEventDate = lastEventDate;
	}

	public PaymentMethodSplitTO getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethodSplitTO paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public CreditorFeesSplitTO getCreditorFees() {
		return creditorFees;
	}

	public void setCreditorFees(CreditorFeesSplitTO creditorFees) {
		this.creditorFees = creditorFees;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(Double extraAmount) {
		this.extraAmount = extraAmount;
	}

	public Long getInstallmentCount() {
		return installmentCount;
	}

	public void setInstallmentCount(Long installmentCount) {
		this.installmentCount = installmentCount;
	}

	public Long getItemCount() {
		return itemCount;
	}

	public void setItemCount(Long itemCount) {
		this.itemCount = itemCount;
	}

	public ItemsSplitTO getItems() {
		return items;
	}

	public void setItems(ItemsSplitTO items) {
		this.items = items;
	}

	public SenderSplitTO getSender() {
		return sender;
	}

	public void setSender(SenderSplitTO sender) {
		this.sender = sender;
	}

	public PrimaryReceiverTO getPrimaryReceiver() {
		return primaryReceiver;
	}

	public void setPrimaryReceiver(PrimaryReceiverTO primaryReceiver) {
		this.primaryReceiver = primaryReceiver;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}
	
	
}
