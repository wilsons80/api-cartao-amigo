package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class CartaoComAssinaturaExpiradaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartaoComAssinaturaExpiradaException(String msg) {
		super(msg);
	}

}
