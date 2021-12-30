package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class PagarmeException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PagarmeException(String msg) {
		super(msg);
	}

}
