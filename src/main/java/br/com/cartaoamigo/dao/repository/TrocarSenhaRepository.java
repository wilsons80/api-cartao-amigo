package br.com.cartaoamigo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cartaoamigo.entity.Usuarios;

@Repository
public interface TrocarSenhaRepository extends JpaRepository<Usuarios, Long>{

	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE Usuarios SET qtdAcessoNegado = 0, stTrocaSenha = false, senha = ?2 WHERE username = ?1" )
	public int updateSenha(String username, String senha);
	
}
