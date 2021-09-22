package br.com.cartaoamigo.to;

public class TrocaSenhaTO {
	
	private String senhaAtual;
	private String senhaNova;
	
	public TrocaSenhaTO() {
	}
	
	public TrocaSenhaTO(String senhaAtual, String senhaNova) {
		super();
		this.senhaAtual = senhaAtual;
		this.senhaNova = senhaNova;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public String getSenhaNova() {
		return senhaNova;
	}
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	
	

}
