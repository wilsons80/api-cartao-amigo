package br.com.cartaoamigo.ws.pagseguro.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authorization")
public class CodigoAutorizacaoTO {

	private String code;
	private String authorizerEmail;
	private PublicKeyPagseguroTO account;

	public CodigoAutorizacaoTO() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAuthorizerEmail() {
		return authorizerEmail;
	}

	public void setAuthorizerEmail(String authorizerEmail) {
		this.authorizerEmail = authorizerEmail;
	}

	public PublicKeyPagseguroTO getAccount() {
		return account;
	}

	public void setAccount(PublicKeyPagseguroTO account) {
		this.account = account;
	}

}
