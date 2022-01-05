package br.com.cartaoamigo.to;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.cartaoamigo.infra.adapter.LocalDateTimeAdapter;

public class NotificacaoTransacaoTO {
	private Long id;
	private String codigoNotificacao;
	private String numeroTransacao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
    private LocalDateTime dtNotificacao;
	
	private StatusTransacaoGatewayPagamentoTO status;
	private Long quantidadeNotificacao;

	private String idAssinaturaPagarme;
	
	public NotificacaoTransacaoTO() {
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

	public StatusTransacaoGatewayPagamentoTO getStatus() {
		return status;
	}

	public void setStatus(StatusTransacaoGatewayPagamentoTO status) {
		this.status = status;
	}

	public Long getQuantidadeNotificacao() {
		return quantidadeNotificacao;
	}

	public void setQuantidadeNotificacao(Long quantidadeNotificacao) {
		this.quantidadeNotificacao = quantidadeNotificacao;
	}
	
	public void addQuantidadeNotificacao() {
		if(Objects.isNull(getQuantidadeNotificacao())) {
			this.quantidadeNotificacao = 0L;
		} 
		
		++this.quantidadeNotificacao;
	}
	
	public String getIdAssinaturaPagarme() {
		return idAssinaturaPagarme;
	}

	public void setIdAssinaturaPagarme(String idAssinaturaPagarme) {
		this.idAssinaturaPagarme = idAssinaturaPagarme;
	}

	@Override
	public String toString() {
		return "NotificacaoTransacaoTO [id=" + id + ", codigoNotificacao=" + codigoNotificacao + ", numeroTransacao="
				+ numeroTransacao + ", dtNotificacao=" + dtNotificacao + ", status=" + status
				+ ", quantidadeNotificacao=" + quantidadeNotificacao + ", idAssinaturaPagarme=" + idAssinaturaPagarme
				+ "]";
	}

	
}
