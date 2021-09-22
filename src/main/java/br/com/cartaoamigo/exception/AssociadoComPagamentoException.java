package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class AssociadoComPagamentoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssociadoComPagamentoException(String msg) {
		super(msg);
	}

}
