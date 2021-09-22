package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class TokenInvalidoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(String msg) {
		super(msg);
	}

}
