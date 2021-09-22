package br.com.cartaoamigo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.LogErro;

@Repository
public interface LogErroRepository extends JpaRepository<LogErro, Long>{

}
