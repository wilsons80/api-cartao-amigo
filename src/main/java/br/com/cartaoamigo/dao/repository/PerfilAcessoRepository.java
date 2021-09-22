package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.PerfilAcesso;

@Repository
public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, Long>{
	
	@Query(value = "SELECT p FROM PerfilAcesso p "
			+ " where p.consulta = true "
			+ "  and p.deleta = false "
			+ "  and p.insere = false "
			+ "  and p.altera = false ")
	public Optional<PerfilAcesso> getPerfilApenasConsulta();

}
