package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Parametros;

@Repository
public interface ParametrosRepository extends JpaRepository<Parametros, Long>{
	

	public Optional<Parametros> findByCodigo(String codigo);
	
}
