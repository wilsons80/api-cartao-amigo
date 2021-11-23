package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class CpfDuplicadoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public CpfDuplicadoException(String msg) {
		super(msg);
	}

}
