package br.com.cartaoamigo.to;

public class DadosUsuarioComAcessoTO {
	
	private Long idPessoaFisica;
	private String nomeCompleto;
	private String email;
	private Long idUsuario;
	private String username;
	
	public DadosUsuarioComAcessoTO() {
	}
	
	
	public DadosUsuarioComAcessoTO(Long idPessoaFisica, String nomeCompleto, String email, Long idUsuario, String username) {
		super();
		this.idPessoaFisica = idPessoaFisica;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.idUsuario = idUsuario;
		this.username = username;
	}
	
	
	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}
	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
