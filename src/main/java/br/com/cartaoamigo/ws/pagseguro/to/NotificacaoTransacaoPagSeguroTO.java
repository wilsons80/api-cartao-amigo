package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "transaction")
public class NotificacaoTransacaoPagSeguroTO {

	private Date date;	
	private String code;
	private String reference;
	private Long type;
	private Long status;

	public NotificacaoTransacaoPagSeguroTO() {
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

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NotificacaoTransacaoPagSeguroTO [date=" + date + ", code=" + code + ", reference=" + reference + ", type=" + type + ", status=" + status + ", getDate()=" + getDate() + ", getCode()=" + getCode() + ", getReference()=" + getReference() + ", getType()=" + getType() + ", getStatus()="
				+ getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
