package br.com.cartaoamigo.ws.pagseguro.to;

public class CodigoAutorizacaoCorretorTO {

	private String notificationCode;
	private String publicKey;

	public CodigoAutorizacaoCorretorTO() {
	}

	public CodigoAutorizacaoCorretorTO(String notificationCode, String publicKey) {
		this.notificationCode = notificationCode;
		this.publicKey = publicKey;
	}

	public String getNotificationCode() {
		return notificationCode;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String toString() {
		return "CodigoAutorizacaoCorretorTO [notificationCode=" + notificationCode + ", publicKey=" + publicKey + "]";
	}

	

	
}
