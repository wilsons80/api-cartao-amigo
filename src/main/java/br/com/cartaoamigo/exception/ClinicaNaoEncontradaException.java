package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ClinicaNaoEncontradaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClinicaNaoEncontradaException(String msg) {
		super(msg);
	}

}
