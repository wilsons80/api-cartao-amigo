package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ArquivoNaoEncontradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArquivoNaoEncontradoException(String msg) {
		super(msg);
	}

}
