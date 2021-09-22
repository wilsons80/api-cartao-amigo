package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ClinicaTipoEspecialidadeNaoEncontradaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClinicaTipoEspecialidadeNaoEncontradaException(String msg) {
		super(msg);
	}

}
