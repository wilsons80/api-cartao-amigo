package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class SemAcessoUnidadeException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SemAcessoUnidadeException(String msg) {
		super(msg);
	}

}
