package br.com.cartaoamigo.to;

public class AcessoTO {

	private Long idModulo;
	private String nomeModulo;
	private Boolean altera;
	private Boolean consulta;
	private Boolean deleta;
	private Boolean insere;

	public AcessoTO() {
	}

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public String getNomeModulo() {
		return nomeModulo;
	}

	public void setNomeModulo(String nomeModulo) {
		this.nomeModulo = nomeModulo;
	}

	public Boolean getAltera() {
		return altera;
	}

	public void setAltera(Boolean altera) {
		this.altera = altera;
	}

	public Boolean getConsulta() {
		return consulta;
	}

	public void setConsulta(Boolean consulta) {
		this.consulta = consulta;
	}

	public Boolean getDeleta() {
		return deleta;
	}

	public void setDeleta(Boolean deleta) {
		this.deleta = deleta;
	}

	public Boolean getInsere() {
		return insere;
	}

	public void setInsere(Boolean insere) {
		this.insere = insere;
	}

}
