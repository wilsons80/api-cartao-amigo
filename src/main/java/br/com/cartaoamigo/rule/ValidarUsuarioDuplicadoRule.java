package br.com.cartaoamigo.rule;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.UsuarioDuplicadoException;

@Component
public class ValidarUsuarioDuplicadoRule {

	@Autowired private UsuarioRepository repository;
	
	public void validar(String username) {
		Optional<List<Usuarios>> duplicateUsuario = repository.findDuplicateByUsernameUsuario(username);
		if(duplicateUsuario.isPresent() && duplicateUsuario.get().size() > 1) {
			throw new UsuarioDuplicadoException("Já existe um usuário com esse login, mude e tente novamente.");
		}
	}
}
