package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class CartaoComAssinaturaVigenteException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CartaoComAssinaturaVigenteException(String msg) {
		super(msg);
	}

}
