package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class CorretorComHistoricoPagamentoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CorretorComHistoricoPagamentoException(String msg) {
		super(msg);
	}

}
