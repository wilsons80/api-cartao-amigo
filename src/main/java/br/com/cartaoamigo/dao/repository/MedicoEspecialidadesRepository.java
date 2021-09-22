package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.MedicoEspecialidades;

@Repository
public interface MedicoEspecialidadesRepository extends JpaRepository<MedicoEspecialidades, Long> {

	
	@Query(value = "select me from MedicoEspecialidades me     "
			+ " where 1=1                                      "
			+ "  and me.idMedico = ?1                            ")
	public Optional<List<MedicoEspecialidades>> findAllByMedico(Long idMedico);
	
	
}
