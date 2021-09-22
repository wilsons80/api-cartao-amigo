package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class TabaleReferenciaEncontradaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TabaleReferenciaEncontradaException(String msg) {
		super(msg);
	}

}
