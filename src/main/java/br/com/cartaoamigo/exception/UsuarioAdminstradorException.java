package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class UsuarioAdminstradorException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioAdminstradorException(String msg) {
		super(msg);
	}

}
