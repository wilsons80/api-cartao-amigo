package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ArquivoNaoSalvoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArquivoNaoSalvoException(String msg) {
		super(msg);
	}

}
