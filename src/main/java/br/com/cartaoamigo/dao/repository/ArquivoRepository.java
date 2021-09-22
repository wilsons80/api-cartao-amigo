package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{
	
	@Query(value = "select a from Arquivo a "
			+ " inner join ArquivosMetadados m on m = a.metadados "
			+ " where m.id = ?1")
	public Optional<Arquivo> findByIdMetadados(Long idMetadados);
	
}
