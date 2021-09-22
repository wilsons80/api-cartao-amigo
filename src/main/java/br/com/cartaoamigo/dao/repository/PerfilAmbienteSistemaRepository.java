package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.PerfilAmbienteSistema;

@Repository
public interface PerfilAmbienteSistemaRepository  extends JpaRepository<PerfilAmbienteSistema, Long>{
	
	@Query(value = "select a from PerfilAmbienteSistema a "
	+ " where a.idUsuario = ?1")
	public Optional<PerfilAmbienteSistema> findByIdUsuario(Long idUsuario);
	
}
