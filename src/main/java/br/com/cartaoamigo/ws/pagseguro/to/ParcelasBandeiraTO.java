package br.com.cartaoamigo.ws.pagseguro.to;

public class ParcelasBandeiraTO {

	private Integer quantity;
	private double  installmentAmount;
	private double  totalAmount;
	private boolean interestFree;
	
	public ParcelasBandeiraTO() {
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean getInterestFree() {
		return interestFree;
	}

	public void setInterestFree(boolean interestFree) {
		this.interestFree = interestFree;
	}
	
	
}
