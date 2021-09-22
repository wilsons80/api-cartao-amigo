package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class NotificacaoPagSeguroException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotificacaoPagSeguroException(String msg) {
		super(msg);
	}

}
