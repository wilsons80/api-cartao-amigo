package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class DiscontoTO {

	private String id;                 // Código do desconto. Formato: dis_XXXXXXXXXXXXXXXX.
	private String value;             //Valor do desconto.
	private String discount_type;      // Tipo do desconto.	Valores possíveis: flat ou percentage. Valor padrão: percentage.
	private Integer cycles;            //Número de vezes que o desconto será aplicado.
    private String item_id;            // Código do Item que receberá o desconto. Se não informado o desconto será aplicado à oda assinatura.   
	private String status;             //Status do desconto. Valores possíveis: active ou deleted
	private LocalDateTime created_at;  //Data da criação do desconto.
	
	public DiscontoTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

	public Integer getCycles() {
		return cycles;
	}

	public void setCycles(Integer cycles) {
		this.cycles = cycles;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
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

	@Override
	public String toString() {
		return "DiscontoTO [id=" + id + ", value=" + value + ", discount_type=" + discount_type + ", cycles=" + cycles
				+ ", item_id=" + item_id + ", status=" + status + ", created_at=" + created_at + "]";
	}

	

}
