package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Assinaturas;

@Repository
public interface AssinaturasRepository extends JpaRepository<Assinaturas, Long> {
	
	@Query("select a from Assinaturas a          "
			+ " where a.ativo = true             "
			+ "   and a.dataCancelamento is null "
			+ "   and a.idTitular = ?1           ")
	Optional<Assinaturas> findAssinaturaAtivaByTitular(Long idTitular);
	
	@Query("select a from Assinaturas a          "
			+ " where a.ativo = true             "
			+ "   and a.dataCancelamento is null "
			+ "   and a.idTitular = ?1           "
			+ "   and upper(a.idCartaoPagarMe) = upper(?2)")
	Optional<Assinaturas> findAssinaturaAtivaByTitularAndIdCartaoPagarMe(Long idTitular, String idCartaoPagarMe);
	
	@Query("select a from Assinaturas a "
			+ " where upper(a.codigoAssinatura) = upper(?1)  ")
	Optional<Assinaturas> findAssinaturaCodigoPagarMe(String codigoAssinatura);

	@Query("select a from Assinaturas a "
			+ " where a.idTitular = ?1  ")
	Optional<List<Assinaturas>> findAllAssinaturasTitular(Long idTitular);
	
}
