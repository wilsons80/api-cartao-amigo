package br.com.cartaoamigo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;

@Entity
@Table(name = "notificacao_transacao")
public class NotificacaoTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_notificacao")
	@SequenceGenerator(name = "sq_id_notificacao", sequenceName = "sq_id_notificacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_notificacao", unique = true, nullable = false, precision = 10)
	private Long id;
	
	@Column(name = "cd_notificacao")
	private String codigoNotificacao;
	
	@Column(name = "nr_transacao")
	private String numeroTransacao;
	
	@Column(name = "dt_notificacao")
	private LocalDateTime dtNotificacao;
	
	@ManyToOne
	@JoinColumn(name = "id_status")
	private StatusTransacaoGatewayPagamento status;
	
	@Column(name = "qtd_notificacao")
	private Long quantidadeNotificacao;
	
	@Column(name = "id_assinatura_pagarme")
	private String idAssinaturaPagarme;
	
	public NotificacaoTransacao() {	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoNotificacao() {
		return codigoNotificacao;
	}

	public void setCodigoNotificacao(String codigoNotificacao) {
		this.codigoNotificacao = codigoNotificacao;
	}

	public String getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(String numerotransacao) {
		this.numeroTransacao = numerotransacao;
	}

	public LocalDateTime getDtNotificacao() {
		return dtNotificacao;
	}

	public void setDtNotificacao(LocalDateTime dtNotificacao) {
		this.dtNotificacao = dtNotificacao;
	}

	public StatusTransacaoGatewayPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusTransacaoGatewayPagamento status) {
		this.status = status;
	}

	public Long getQuantidadeNotificacao() {
		return quantidadeNotificacao;
	}

	public void setQuantidadeNotificacao(Long quantidadeNotificacao) {
		this.quantidadeNotificacao = quantidadeNotificacao;
	}

	public String getIdAssinaturaPagarme() {
		return idAssinaturaPagarme;
	}

	public void setIdAssinaturaPagarme(String idAssinaturaPagarme) {
		this.idAssinaturaPagarme = idAssinaturaPagarme;
	}
	
	
}
