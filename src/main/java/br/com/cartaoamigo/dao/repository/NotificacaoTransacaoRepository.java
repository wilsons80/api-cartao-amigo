package br.com.cartaoamigo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cartaoamigo.entity.NotificacaoTransacao;

@Repository
public interface NotificacaoTransacaoRepository extends JpaRepository<NotificacaoTransacao, Long> {
	
	@Query(value = "select nt from NotificacaoTransacao nt "
			+ " where nt.numeroTransacao = ?1" )

	Optional<NotificacaoTransacao> findByNumerotransacao(String numerotransacao);
	
	@Query(value = "select nt from NotificacaoTransacao nt "
			+ " where nt.codigoNotificacao = ?1" )
	
	Optional<NotificacaoTransacao> findByCodigoNotificacao(String codigoNotificacao);

}
