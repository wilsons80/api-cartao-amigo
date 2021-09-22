package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
	
	@Query(value = "select c from Clinica c "
			+ " where c.ativo = ?1" )
	public Optional<List<Clinica>> findAllByStatus(Boolean status);
	
	
	@Query(value = "select c from Clinica c     "
			+ " where upper(c.bairro) = upper(?1)      " )
	public Optional<List<Clinica>> findAllByBairro(String bairro);
	
	
	@Query(value = "select c from Clinica c                                  "
			+ " inner join ClinicasTipoEspecialidades cte on cte.clinica = c "
			+ " where cte.id = ?1                                            " )
	public Optional<List<Clinica>> findAllByTipoEspecialidade(Long idTipoEspecialidade);
	
	@Query(value = "select c from Clinica c                                  "
			+ " inner join ClinicasTipoEspecialidades cte on cte.clinica = c "
			+ " where cte.valorAssociado = ?1                                " )
	public Optional<List<Clinica>> findAllByValorAssociado(Double valor);
	
	
	@Query(value = "select c from Clinica c                                  "
			+ " inner join ClinicasTipoEspecialidades cte on cte.clinica = c "
			+ " where cte.valorParticular = ?1                               " )
	public Optional<List<Clinica>> findAllByValorParticular(Double valor);
	
}
