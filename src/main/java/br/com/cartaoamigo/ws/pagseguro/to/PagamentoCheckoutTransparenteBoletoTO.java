package br.com.cartaoamigo.ws.pagseguro.to;

public class PagamentoCheckoutTransparenteBoletoTO {

	//Dados do plano escolhido
	private Long   idPlano;
	private String descricaoPlano;
	private Double valorPlano;
	
	private String reference;
	
	//(Tela) Dados do comprador
	private String nomeComprador;
	private String cpfComprador;
	private String codAreaComprador;
	private String telefoneComprador;
	private String emailComprador;
	
	//Identificador do comprador
	private String senderHash;
	
	//(Sistema) Dados do endereço de cobrança
	private String enderecoCobranca;
	private String numeroCobranca;
	private String complementoCobranca;
	private String distritoCobranca;
	private String codPostalCobranca;
	private String cidadeCobranca;
	private String estadoCobranca;
	private String paisCobranca;
	
	// Dados do corretor
	private boolean isPorcentagemCorretor;
	private String  publicKeyCorretor;
	private double  valorCorretor;
	private String  codigoCorretor;
	
	public PagamentoCheckoutTransparenteBoletoTO() {
	}

	public Long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}

	public String getDescricaoPlano() {
		return descricaoPlano;
	}

	public void setDescricaoPlano(String descricaoPlano) {
		this.descricaoPlano = descricaoPlano;
	}

	public Double getValorPlano() {
		return valorPlano;
	}

	public void setValorPlano(Double valorPlano) {
		this.valorPlano = valorPlano;
	}

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nome) {
		this.nomeComprador = nome;
	}

	public String getCpfComprador() {
		return cpfComprador;
	}

	public void setCpfComprador(String cpf) {
		this.cpfComprador = cpf;
	}

	public String getCodAreaComprador() {
		return codAreaComprador;
	}

	public void setCodAreaComprador(String codArea) {
		this.codAreaComprador = codArea;
	}

	public String getTelefoneComprador() {
		return telefoneComprador;
	}

	public void setTelefoneComprador(String telefone) {
		this.telefoneComprador = telefone;
	}

	public String getEmailComprador() {
		return emailComprador;
	}

	public void setEmailComprador(String email) {
		this.emailComprador = email;
	}

	public String getSenderHash() {
		return senderHash;
	}

	public void setSenderHash(String senderHash) {
		this.senderHash = senderHash;
	}

	public String getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(String endereco) {
		this.enderecoCobranca = endereco;
	}

	public String getNumeroCobranca() {
		return numeroCobranca;
	}

	public void setNumeroCobranca(String numero) {
		this.numeroCobranca = numero;
	}

	public String getComplementoCobranca() {
		return complementoCobranca;
	}

	public void setComplementoCobranca(String complemento) {
		this.complementoCobranca = complemento;
	}

	public String getDistritoCobranca() {
		return distritoCobranca;
	}

	public void setDistritoCobranca(String distrito) {
		this.distritoCobranca = distrito;
	}

	public String getCodPostalCobranca() {
		return codPostalCobranca;
	}

	public void setCodPostalCobranca(String codPostal) {
		this.codPostalCobranca = codPostal;
	}

	public String getCidadeCobranca() {
		return cidadeCobranca;
	}

	public void setCidadeCobranca(String cidade) {
		this.cidadeCobranca = cidade;
	}

	public String getEstadoCobranca() {
		return estadoCobranca;
	}

	public void setEstadoCobranca(String estado) {
		this.estadoCobranca = estado;
	}

	public String getPaisCobranca() {
		return paisCobranca;
	}

	public void setPaisCobranca(String pais) {
		this.paisCobranca = pais;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public boolean getIsPorcentagemCorretor() {
		return isPorcentagemCorretor;
	}

	public void setIsPorcentagemCorretor(boolean isPorcentagemCorretor) {
		this.isPorcentagemCorretor = isPorcentagemCorretor;
	}

	public String getPublicKeyCorretor() {
		return publicKeyCorretor;
	}

	public void setPublicKeyCorretor(String publicKeyCorretor) {
		this.publicKeyCorretor = publicKeyCorretor;
	}

	public double getValorCorretor() {
		return valorCorretor;
	}

	public void setValorCorretor(double valorCorretor) {
		this.valorCorretor = valorCorretor;
	}

	public String getCodigoCorretor() {
		return codigoCorretor;
	}

	public void setCodigoCorretor(String codigoCorretor) {
		this.codigoCorretor = codigoCorretor;
	}

	@Override
	public String toString() {
		return "PagamentoCheckoutTransparenteBoletoTO [idPlano=" + idPlano + ", descricaoPlano=" + descricaoPlano
				+ ", valorPlano=" + valorPlano + ", reference=" + reference + ", nomeComprador=" + nomeComprador
				+ ", cpfComprador=" + cpfComprador + ", codAreaComprador=" + codAreaComprador + ", telefoneComprador="
				+ telefoneComprador + ", emailComprador=" + emailComprador + ", senderHash=" + senderHash
				+ ", enderecoCobranca=" + enderecoCobranca + ", numeroCobranca=" + numeroCobranca
				+ ", complementoCobranca=" + complementoCobranca + ", distritoCobranca=" + distritoCobranca
				+ ", codPostalCobranca=" + codPostalCobranca + ", cidadeCobranca=" + cidadeCobranca
				+ ", estadoCobranca=" + estadoCobranca + ", paisCobranca=" + paisCobranca + ", isPorcentagemCorretor="
				+ isPorcentagemCorretor + ", publicKeyCorretor=" + publicKeyCorretor + ", valorCorretor="
				+ valorCorretor + ", codigoCorretor=" + codigoCorretor + "]";
	}
	
	
	
}
