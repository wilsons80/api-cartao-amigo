package br.com.cartaoamigo.rule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.UsuarioSemAcessoException;

@Component
public class UsuarioSistemaNaoEncontradoRule {

	public void verificar(Optional<Usuarios> usuarioSistema) {
		if(!usuarioSistema.isPresent()) {
			throw new UsuarioSemAcessoException("O usuário informado não existe.");
		}
	}

}
