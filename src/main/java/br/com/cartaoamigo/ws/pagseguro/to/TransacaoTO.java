package br.com.cartaoamigo.ws.pagseguro.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.cartaoamigo.infra.adapter.LocalDateTimeAdapter;

public class TransacaoTO {

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime date;
	private List<String> code;
	private List<String> reference;
	private List<String> type;
	private	List<String> status;
	private LocalDateTime lastEventDate;
	private List<PaymentMethodTO> paymentMethod;
	private List<String> grossAmount;
	private List<String> discountAmount;
	private List<String> feeAmount;
	private List<String> netAmount;
	private List<String> extraAmount;
	private LocalDateTime escrowEndDate;
	private List<String> installmentCount;
	private List<String> itemCount;
	private List<ItemsTO>  items;
	private List<SenderTO> sender;
	private List<GatewaySystemTO> gatewaySystem;
	private PrimaryReceiverTO primaryReceiver;
	
	public TransacaoTO() {
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public List<String> getCode() {
		return code;
	}


	public void setCode(List<String> code) {
		this.code = code;
	}


	public List<String> getReference() {
		return reference;
	}


	public void setReference(List<String> reference) {
		this.reference = reference;
	}


	public List<String> getType() {
		return type;
	}


	public void setType(List<String> type) {
		this.type = type;
	}


	public List<String> getStatus() {
		return status;
	}


	public void setStatus(List<String> status) {
		this.status = status;
	}


	public LocalDateTime getLastEventDate() {
		return lastEventDate;
	}


	public void setLastEventDate(LocalDateTime lastEventDate) {
		this.lastEventDate = lastEventDate;
	}


	public List<PaymentMethodTO> getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(List<PaymentMethodTO> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public List<String> getGrossAmount() {
		return grossAmount;
	}


	public void setGrossAmount(List<String> grossAmount) {
		this.grossAmount = grossAmount;
	}


	public List<String> getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(List<String> discountAmount) {
		this.discountAmount = discountAmount;
	}


	public List<String> getFeeAmount() {
		return feeAmount;
	}


	public void setFeeAmount(List<String> feeAmount) {
		this.feeAmount = feeAmount;
	}


	public List<String> getNetAmount() {
		return netAmount;
	}


	public void setNetAmount(List<String> netAmount) {
		this.netAmount = netAmount;
	}


	public List<String> getExtraAmount() {
		return extraAmount;
	}


	public void setExtraAmount(List<String> extraAmount) {
		this.extraAmount = extraAmount;
	}


	public LocalDateTime getEscrowEndDate() {
		return escrowEndDate;
	}


	public void setEscrowEndDate(LocalDateTime escrowEndDate) {
		this.escrowEndDate = escrowEndDate;
	}


	public List<String> getInstallmentCount() {
		return installmentCount;
	}


	public void setInstallmentCount(List<String> installmentCount) {
		this.installmentCount = installmentCount;
	}


	public List<String> getItemCount() {
		return itemCount;
	}


	public void setItemCount(List<String> itemCount) {
		this.itemCount = itemCount;
	}


	public List<ItemsTO> getItems() {
		return items;
	}


	public void setItems(List<ItemsTO> items) {
		this.items = items;
	}


	public List<SenderTO> getSender() {
		return sender;
	}


	public void setSender(List<SenderTO> sender) {
		this.sender = sender;
	}


	public List<GatewaySystemTO> getGatewaySystem() {
		return gatewaySystem;
	}


	public void setGatewaySystem(List<GatewaySystemTO> gatewaySystem) {
		this.gatewaySystem = gatewaySystem;
	}


	public PrimaryReceiverTO getPrimaryReceiver() {
		return primaryReceiver;
	}


	public void setPrimaryReceiver(PrimaryReceiverTO primaryReceiver) {
		this.primaryReceiver = primaryReceiver;
	}

	
}
