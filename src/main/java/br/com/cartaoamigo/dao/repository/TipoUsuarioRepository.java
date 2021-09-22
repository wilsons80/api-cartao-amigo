package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.TipoUsuario;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

	public Optional<TipoUsuario> findByDescricao(String descricao);
	
}
