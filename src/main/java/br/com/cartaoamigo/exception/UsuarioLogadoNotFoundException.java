package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class UsuarioLogadoNotFoundException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioLogadoNotFoundException(String msg) {
		super(msg);
	}

}
