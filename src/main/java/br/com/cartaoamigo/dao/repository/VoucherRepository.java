package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cartaoamigo.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher,Long>{
	
	
	@Query(value = "select v from Voucher v "
			+ " where upper(v.codigo) = upper(?1)" )
	public Optional<Voucher> findByCodigo(String codigo);

	
	
}
