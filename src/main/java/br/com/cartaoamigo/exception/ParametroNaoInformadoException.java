package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ParametroNaoInformadoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametroNaoInformadoException(String msg) {
		super(msg);
	}

}
