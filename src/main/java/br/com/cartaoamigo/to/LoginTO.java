package br.com.cartaoamigo.to;
// Generated 12/08/2019 22:11:24 by Hibernate Tools 5.3.0.Beta2

public class LoginTO {
	
	private String login;
	private String senha;
	private String idsession;
	
	public LoginTO() {
	}

	public LoginTO(String userName, String senha) {
		super();
		this.login = userName;
		this.senha = senha;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String userName) {
		this.login = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIdsession() {
		return idsession;
	}

	public void setIdsession(String idsession) {
		this.idsession = idsession;
	}

	
	
}
