package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.MedicosClinicas;

@Repository
public interface MedicosClinicasRepository extends JpaRepository<MedicosClinicas, Long> {

	
	@Query(value = "select mc from MedicosClinicas mc   "
			+ " inner join Clinica clinica on clinica = mc.clinica "
			+ " where clinica.id = ?1                               ")
	public Optional<List<MedicosClinicas>> findAllByClinica(Long idClinica);
	
	
	@Query(value = "select mc from MedicosClinicas mc   "
			+ " inner join Clinica clinica on clinica = mc.clinica "
			+ " where clinica.id = ?1                               ")
	public Optional<MedicosClinicas> findByClinicaAndIdMedico(Long idClinica, Long IdMedico);
	
}
