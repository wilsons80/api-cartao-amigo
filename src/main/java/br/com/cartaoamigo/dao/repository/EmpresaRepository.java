package br.com.cartaoamigo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	

}
