package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class GruposModuloDuplicadoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GruposModuloDuplicadoException(String msg) {
		super(msg);
	}

}
