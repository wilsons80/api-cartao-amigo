package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.TipoEspecialidade;

@Repository
public interface TipoEspecialidadeRepository extends JpaRepository<TipoEspecialidade, Long> {
	
	
	@Query("select te from TipoEspecialidade te "
			+ " order by te.descricao")
	Optional<List<TipoEspecialidade>> getListaAll();

}
