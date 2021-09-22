package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class GrupoAcessoJaAtribuidoParaUsuarioException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GrupoAcessoJaAtribuidoParaUsuarioException(String msg) {
		super(msg);
	}

}
