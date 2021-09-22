package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class EmailNaoEnviadoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailNaoEnviadoException(String msg) {
		super(msg);
	}

}
