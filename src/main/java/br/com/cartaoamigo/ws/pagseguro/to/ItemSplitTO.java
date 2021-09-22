package br.com.cartaoamigo.ws.pagseguro.to;

public class ItemSplitTO {
	
	private String id;
	private String description;
	private Long   quantity;
	private Double amount;
	
	
	public ItemSplitTO() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
