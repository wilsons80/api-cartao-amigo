package br.com.cartaoamigo.ws.pagarme.to;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.cartaoamigo.infra.util.StringUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartaoClienteTO {

	private String id;
	private String first_six_digits;
	private String last_four_digits;
	private String brand;
	private String holder_name;
	private String holder_document;
    private String exp_month;
    private Integer exp_year;
    private LocalDateTime created_at;
    private String type;
	private String status;
	
	private BandeiraCartaoTO bandeiraCartaoTO;
	private EnderecoCobrancaCartaoClientePagarMeTO billing_address;
	
	private boolean expirado;

	public CartaoClienteTO() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_six_digits() {
		return first_six_digits;
	}

	public void setFirst_six_digits(String first_six_digits) {
		this.first_six_digits = first_six_digits;
	}

	public String getLast_four_digits() {
		return last_four_digits;
	}

	public void setLast_four_digits(String last_four_digits) {
		this.last_four_digits = last_four_digits;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}

	public String getExp_month() {
		return StringUtil.lpad(String.valueOf(exp_month), "0", 2);
	}

	public void setExp_month(String exp_month) {
		this.exp_month = exp_month;
	}

	public Integer getExp_year() {
		return exp_year;
	}

	public void setExp_year(Integer exp_year) {
		this.exp_year = exp_year;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public BandeiraCartaoTO getBandeiraCartaoTO() {
		return bandeiraCartaoTO;
	}

	public void setBandeiraCartaoTO(BandeiraCartaoTO bandeiraCartaoTO) {
		this.bandeiraCartaoTO = bandeiraCartaoTO;
	}

	public EnderecoCobrancaCartaoClientePagarMeTO getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(EnderecoCobrancaCartaoClientePagarMeTO billing_address) {
		this.billing_address = billing_address;
	}

	public String getHolder_document() {
		return holder_document;
	}

	public void setHolder_document(String holder_document) {
		this.holder_document = holder_document;
	}

	public boolean expirado() {
		int mesCartao = Integer.valueOf(getExp_month());
		int anoCartao = getExp_year();
		
		LocalDate agora = LocalDate.now();
		int anoAtual = agora.getYear();
		int mesAtual = agora.getMonthValue();

		if(anoAtual > anoCartao) {
			expirado = true;
		} else if (anoAtual == anoCartao && mesAtual > mesCartao) {
			expirado = true;
		} else {
			expirado = false;
		}
		
		return expirado;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CartaoClienteTO [id=" + id + ", first_six_digits=" + first_six_digits + ", last_four_digits="
				+ last_four_digits + ", brand=" + brand + ", holder_name=" + holder_name + ", holder_document="
				+ holder_document + ", exp_month=" + exp_month + ", exp_year=" + exp_year + ", created_at=" + created_at
				+ ", type=" + type + ", status=" + status + ", bandeiraCartaoTO=" + bandeiraCartaoTO
				+ ", billing_address=" + billing_address + ", expirado=" + expirado + "]";
	}
	
	
}
