package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ModuloNaoEncontradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModuloNaoEncontradoException(String msg) {
		super(msg);
	}

}
