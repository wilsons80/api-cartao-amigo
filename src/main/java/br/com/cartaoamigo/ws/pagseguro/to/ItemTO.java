package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class ItemTO {
	private List<String> id;
	private List<String> description;
	private List<String> quantity;
	private List<String> amount;
	
	
	public ItemTO() {
		super();
	}
	public List<String> getId() {
		return id;
	}
	public void setId(List<String> id) {
		this.id = id;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public List<String> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<String> quantity) {
		this.quantity = quantity;
	}
	public List<String> getAmount() {
		return amount;
	}
	public void setAmount(List<String> amount) {
		this.amount = amount;
	}
	

}
