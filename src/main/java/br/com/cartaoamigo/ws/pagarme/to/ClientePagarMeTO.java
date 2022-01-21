package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDateTime;

public class ClientePagarMeTO {

	private String id;
	private String name;
	private String email;
	private String code;
	private String document;
	private String document_type;
	private String type;
	private String gender;
	private Boolean delinquent;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private String birthdate;

	private EnderecoClientePagarMeTO address;
	private TelefoneClientePagarMeTO phones;

	public ClientePagarMeTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getDelinquent() {
		return delinquent;
	}

	public void setDelinquent(Boolean delinquent) {
		this.delinquent = delinquent;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public EnderecoClientePagarMeTO getAddress() {
		return address;
	}

	public void setAddress(EnderecoClientePagarMeTO address) {
		this.address = address;
	}

	public TelefoneClientePagarMeTO getPhones() {
		return phones;
	}

	public void setPhones(TelefoneClientePagarMeTO phones) {
		this.phones = phones;
	}

}
