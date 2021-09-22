package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;

@Repository
public interface ClinicasTipoEspecialidadesRepository extends JpaRepository<ClinicasTipoEspecialidades, Long> {
	
	
	@Query(value = "select cte from ClinicasTipoEspecialidades cte   "
			+ " inner join Clinica clinica on clinica = cte.clinica "
			+ " where clinica.id = ?1                               ")
	public Optional<List<ClinicasTipoEspecialidades>> findAllByClinica(Long idClinica);
	
	
	
	
	@Query(value = "select cte from ClinicasTipoEspecialidades cte   "
			+ " inner join Clinica clinica on clinica = cte.clinica "
			+ " inner join TipoEspecialidade te on te = cte.tipoEspecialidade "
			+ " where clinica.id = ?1                                         "
			+ "   and te.id = ?2                                              ")
	public Optional<ClinicasTipoEspecialidades> findByClinicaAndTipoEspecialidade(Long idClinica, Long idTipoEspecialidade);
	
	
	
	
	
	@Query(value = "select cte from ClinicasTipoEspecialidades cte         	 "
			+ " inner join TipoEspecialidade te on te = cte.tipoEspecialidade "
			+ " where te.id = ?1                                            " )
	public Optional<List<ClinicasTipoEspecialidades>> findAllByTipoEspecialidade(Long idTipoEspecialidade);
	
}
