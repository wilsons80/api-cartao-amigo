package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.MedicosEspecialiadadesClinicas;

@Repository
public interface MedicosEspecialidadesClinicasRepository extends JpaRepository<MedicosEspecialiadadesClinicas, Long> {


	@Query(value = "select mec from MedicosEspecialiadadesClinicas mec    "
			+ " inner join MedicosClinicas mc on mc = mec.medicosClinicas "
			+ " inner join Clinica clinica on clinica = mc.clinica        "
			+ " where clinica.id = ?1                                     ")
	public Optional<List<MedicosEspecialiadadesClinicas>> findAllByClinica(Long idClinica);

	
}
