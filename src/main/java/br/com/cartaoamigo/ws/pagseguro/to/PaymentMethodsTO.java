package br.com.cartaoamigo.ws.pagseguro.to;

public class PaymentMethodsTO {
	
	private String name;
	private String code;
	private OpcoesPagamentoTO options;	

	public PaymentMethodsTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public OpcoesPagamentoTO getOptions() {
		return options;
	}

	public void setOptions(OpcoesPagamentoTO options) {
		this.options = options;
	}


}
