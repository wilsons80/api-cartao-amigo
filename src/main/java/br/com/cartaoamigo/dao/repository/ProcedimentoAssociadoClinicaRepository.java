package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.ProcedimentoAssociadoClinica;

@Repository
public interface ProcedimentoAssociadoClinicaRepository extends JpaRepository<ProcedimentoAssociadoClinica, Long> {

	@Query(value = "select pac from ProcedimentoAssociadoClinica pac                                  "
			+ " inner join ClinicasTipoEspecialidades cte on cte = pac.clinicaTipoEspecidades         "
			+ " inner join Clinica clinica on clinica = cte.clinica                                   "
			+ " where clinica.id = ?1                                                                 ")
	public Optional<List<ProcedimentoAssociadoClinica>> findAllByClinica(Long idClinica);

}
