/**
 * Projeto   : GeradorDeRelatorio
 * Pacote    : br.jus.trt10.relatorio.util
 * Classe    : TipoRelatorio.java
 * Autor     : wilson.souza
 * Data      : 04/10/2016
 * Descrição : 
 * 
 * Modificações 
 * 
 * Responsavel  Data        Descrição
 * ===========  ==========  =====================================================               
 *
 */

package br.com.cartaoamigo.infra.relatorio;

public enum TipoRelatorio {
	
	PDF(1,"pdf"),
	XLS(2,"xls");

	private String tipo;
	private int    codArquivo;
	
	private TipoRelatorio(int pCodArquivo, String pTipo){
		tipo = pTipo;
		codArquivo = pCodArquivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String extensao) {
		this.tipo = extensao;
	}

	public int getCodArquivo() {
		return codArquivo;
	}

	public void setCodArquivo(int codArquivo) {
		this.codArquivo = codArquivo;
	}

	public static TipoRelatorio getPorTipo(String tipo) {
		for (TipoRelatorio tr: values()) {
			if (tr.getTipo().equals(tipo)) {
				return tr;
			}
		}
		return null;
	}
	
}


