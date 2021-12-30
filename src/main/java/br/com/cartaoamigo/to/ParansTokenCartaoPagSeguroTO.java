package br.com.cartaoamigo.to;

public class ParansTokenCartaoPagSeguroTO {
	private String idSessao; 
    private Double valor;
    private String numeroCartao; 
    private String bandeiraCartao; 
    private String cvv;
    private String mesVencimentoCartao; 
    private String anoVencimentoCartao;
    
    public ParansTokenCartaoPagSeguroTO() {
	}

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getMesVencimentoCartao() {
		return mesVencimentoCartao;
	}

	public void setMesVencimentoCartao(String mesVencimentoCartao) {
		this.mesVencimentoCartao = mesVencimentoCartao;
	}

	public String getAnoVencimentoCartao() {
		return anoVencimentoCartao;
	}

	public void setAnoVencimentoCartao(String anoVencimentoCartao) {
		this.anoVencimentoCartao = anoVencimentoCartao;
	}
    
    
}
