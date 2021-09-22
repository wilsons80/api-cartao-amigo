package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class PessoaFisicaJaExisteException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaFisicaJaExisteException(String msg) {
		super(msg);
	}

}
