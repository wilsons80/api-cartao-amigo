package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class CreditorFeesSplitTO {

	private Double installmentFeeAmount;
	private Double intermediationRateAmount;
	private Double intermediationFeeAmount;
	
	public CreditorFeesSplitTO() {
	}

	public Double getInstallmentFeeAmount() {
		return installmentFeeAmount;
	}

	public void setInstallmentFeeAmount(Double installmentFeeAmount) {
		this.installmentFeeAmount = installmentFeeAmount;
	}

	public Double getIntermediationRateAmount() {
		return intermediationRateAmount;
	}

	public void setIntermediationRateAmount(Double intermediationRateAmount) {
		this.intermediationRateAmount = intermediationRateAmount;
	}

	public Double getIntermediationFeeAmount() {
		return intermediationFeeAmount;
	}

	public void setIntermediationFeeAmount(Double intermediationFeeAmount) {
		this.intermediationFeeAmount = intermediationFeeAmount;
	}
	
	
	
}
