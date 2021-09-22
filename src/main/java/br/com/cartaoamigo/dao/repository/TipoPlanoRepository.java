package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.TipoPlano;

@Repository
public interface TipoPlanoRepository extends JpaRepository<TipoPlano, Long> {
	
	@Query("select tp from TipoPlano tp "
			+ " where tp.ativo = true ")
	Optional<List<TipoPlano>> findAllAtivos();
	

}
