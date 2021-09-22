package br.com.cartaoamigo.exception;

import br.com.cartaoamigo.exception.base.NegocioException;

public class UsuarioSemAcessoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public UsuarioSemAcessoException(String msg) {
		super(msg);
	}

}
