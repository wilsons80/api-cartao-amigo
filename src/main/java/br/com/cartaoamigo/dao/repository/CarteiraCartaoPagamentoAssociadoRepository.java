package br.com.cartaoamigo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;

public interface CarteiraCartaoPagamentoAssociadoRepository extends JpaRepository<CarteiraCartaoPagamentoAssociado,Long>{
	
	
	@Query(value = "select c from CarteiraCartaoPagamentoAssociado c "
			+ " where c.idTitular         = ?1 "
			+ "   and c.primeiros6digitos = ?2"
			+ "   and c.ultimos4digitos   = ?3" )
	public Optional<CarteiraCartaoPagamentoAssociado> findByIdTitularAndDigitosCartao(Long idTitular, String primeiros6digitos, String ultimos4digitos);
	
	@Query(value = "select c from CarteiraCartaoPagamentoAssociado c "
			+ " where upper(c.idCartaoPagarMe) = upper(?1)" )
	public Optional<CarteiraCartaoPagamentoAssociado> findByIdCartaoPagarme(String idCartaoPagarme);

	@Query(value = "select c from CarteiraCartaoPagamentoAssociado c "
			+ " where upper(c.idCartaoPagarMe) = upper(?1)"
			+ "   and c.idTitular = ?2" )
	public Optional<CarteiraCartaoPagamentoAssociado> findByIdCartaoPagarmeAndIdTitular(String idCartaoPagarme, Long idTitular);

	
	@Query(value = "select c from CarteiraCartaoPagamentoAssociado c "
			    + " where c.idTitular = ?1 "
			    + "   and c.exclusaoLogica = false")
	public Optional<List<CarteiraCartaoPagamentoAssociado>> findAllAtivoByTitular(Long idTitular);
	
	@Query(value = "select c from CarteiraCartaoPagamentoAssociado c "
			     + " where c.idClientePagarMe = ?1 "
			     + "   and c.exclusaoLogica = false")
	public Optional<List<CarteiraCartaoPagamentoAssociado>> findAllAtivoByClientePagarMe(String idClientePagarme);

	
	
}
