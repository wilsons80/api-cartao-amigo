package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class RedefinirSenhaPeriodoExpiradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedefinirSenhaPeriodoExpiradoException(String msg) {
		super(msg);
	}

}
