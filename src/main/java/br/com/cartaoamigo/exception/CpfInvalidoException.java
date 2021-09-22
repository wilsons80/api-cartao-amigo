package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class CpfInvalidoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public CpfInvalidoException(String msg) {
		super(msg);
	}

}
