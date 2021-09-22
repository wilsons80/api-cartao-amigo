package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class ProcedimentoAssociadoClinicaJaExisteException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcedimentoAssociadoClinicaJaExisteException(String msg) {
		super(msg);
	}

}
