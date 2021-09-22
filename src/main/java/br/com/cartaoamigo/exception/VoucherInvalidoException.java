package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class VoucherInvalidoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public VoucherInvalidoException(String msg) {
		super(msg);
	}

}
