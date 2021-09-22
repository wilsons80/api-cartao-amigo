package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class TrocaSenhaInvalidaException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public TrocaSenhaInvalidaException(String msg) {
		super(msg);
	}

}
