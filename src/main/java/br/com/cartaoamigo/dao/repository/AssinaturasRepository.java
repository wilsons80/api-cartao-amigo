package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Assinaturas;

@Repository
public interface AssinaturasRepository extends JpaRepository<Assinaturas, Long> {
	
	@Query("select tp from Assinaturas a "
			+ " where a.ativo = true     "
			+ "   and a.id_titular = ?1  ")
	Optional<Assinaturas> findAssinaturaAtivaByTitular(Long idTitular);
	
	@Query("select tp from Assinaturas a "
			+ " where a.codigoAssinatura = ?1  ")
	Optional<Assinaturas> findAssinaturaCodigoPagarMe(String codigoAssinatura);

	@Query("select tp from Assinaturas a "
			+ " where a.id_titular = ?1  ")
	Optional<List<Assinaturas>> findAllAssinaturasTitular(Long idTitular);
	
}
