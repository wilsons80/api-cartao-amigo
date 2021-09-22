package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class TitularJaExisteException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public TitularJaExisteException(String msg) {
		super(msg);
	}

}
