package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class QuantidadesDependentesInvalidException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuantidadesDependentesInvalidException(String message) {
		super(message);
		
	}

}
