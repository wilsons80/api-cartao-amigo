package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class UsuarioJaExisteException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioJaExisteException(String msg) {
		super(msg);
	}

}
