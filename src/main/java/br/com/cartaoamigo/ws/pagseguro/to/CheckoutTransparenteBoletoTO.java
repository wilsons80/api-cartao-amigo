package br.com.cartaoamigo.ws.pagseguro.to;

public class CheckoutTransparenteBoletoTO {

	//Dados do plano escolhido
	private Long   idPlano;
	
	//(Tela) Dados do comprador
	private String cpfComprador;
	
	//Identificador do comprador
	private String senderHash;
	
	// Dados do corretor
	private String  codigoCorretor;
	
	// Voucher de desconto
	private String voucher;
	
	
	public CheckoutTransparenteBoletoTO() {
	}

	
	
	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}

	public String getCpfComprador() {
		return cpfComprador;
	}

	public void setCpfComprador(String cpfComprador) {
		this.cpfComprador = cpfComprador;
	}

	public String getSenderHash() {
		return senderHash;
	}

	public void setSenderHash(String senderHash) {
		this.senderHash = senderHash;
	}

	public String getCodigoCorretor() {
		return codigoCorretor;
	}

	public void setCodigoCorretor(String codigoCorretor) {
		this.codigoCorretor = codigoCorretor;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	

}
