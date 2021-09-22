package br.com.cartaoamigo.exception.base;


public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1142286325546768599L;

	public NegocioException(String message) {
		super(message);
	}

}
