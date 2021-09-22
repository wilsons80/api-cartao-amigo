package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cartaoamigo.entity.RedefinirSenha;

public interface RedefinirSenhaRepository extends JpaRepository<RedefinirSenha,Long>{
	
	
	@Query(value = "select r from RedefinirSenha r "
			+ " where r.codigoValidacao = ?1" )
	public Optional<RedefinirSenha> findByCodigoValidacao(String codigoValidacao);
	
	
}
