package br.com.cartaoamigo.ws.pagarme.to;

public class TelefoneClientePagarMeTO {

	private DadosTelefoneClientePagarMeTO home_phone;
	private DadosTelefoneClientePagarMeTO mobile_phone;

	public TelefoneClientePagarMeTO() {
	}

	public DadosTelefoneClientePagarMeTO getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(DadosTelefoneClientePagarMeTO home_phone) {
		this.home_phone = home_phone;
	}

	public DadosTelefoneClientePagarMeTO getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(DadosTelefoneClientePagarMeTO mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

}
