package br.com.cartaoamigo.to.pagarme;

import java.util.Arrays;

public class NotificacaoGatewayRepostaTO {
	
	private String errors;
	private String[] erros;
	
	public NotificacaoGatewayRepostaTO() {
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String[] getErros() {
		return erros;
	}

	public void setErros(String[] erros) {
		this.erros = erros;
	}

	@Override
	public String toString() {
		return "NotificacaoGatewayRepostaTO [errors=" + errors + ", erros=" + Arrays.toString(erros) + "]";
	}

	
}
