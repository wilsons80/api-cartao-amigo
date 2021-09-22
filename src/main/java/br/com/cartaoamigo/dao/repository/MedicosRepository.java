package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Medico;

@Repository
public interface MedicosRepository extends JpaRepository<Medico, Long> {
	
	
	@Query(value = "select m from Medico m                     "
			+ " inner join MedicosClinicas mc on mc.medico = m "
			+ " inner join Clinica c on c = mc.clinica         "
			+ " where 1=1                                      "
			+ "   and c.id = ?1                                ")
	Optional<List<Medico>> findAllByClinica(Long idClinica);

}
