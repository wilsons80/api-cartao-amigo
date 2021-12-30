package br.com.cartaoamigo.ws.pagarme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.infra.util.NumeroUtil;

@Component
public class PagarmeRecorrenciaProvider {
	
	@Value("${pagarme.aplicacao.appId}")                                  private String appId;

	
	@Value("${pagarme.recorrencia.urlTokenCartao}")                        private String tokenCartao;
	@Value("${pagarme.recorrencia.bandeira.cartao}")                       private String bandeiraCartao;
	
	
	@Value("${pagseguro.split.solicitar.autorizacao}")                     private String solicitarAutorizacao;
	@Value("${pagseguro.split.direct.autorizacao}")                        private String direcionarAutorizacao;
	@Value("${pagseguro.split.consultar.autorizacao}")                     private String consultarAutorizacao;
	@Value("${pagseguro.split.opcoes.parcelamento}")                       private String opcoesParcelamento;
	@Value("${pagseguro.split.checkout.cartaocredito}")                    private String checkoutPagamentoCartaoCredito;
	@Value("${pagseguro.split.consultar.notificacao}")                     private String consultarNotificacao;
	@Value("${pagseguro.split.checkout.boleto}")                           private String checkoutPagamentoBoleto;
	
	
	
	public String getUrlBandeira(String binCartao) {
		return String.format(bandeiraCartao, binCartao );
	}
	
	public String getUrlTokenCartao() {
		return String.format(tokenCartao, appId );
	}
	
	
	/*
	public String getUrlConsultarNotificacao(String codigoNotificacao) {
		return String.format(consultarNotificacao, codigoNotificacao, appId, appKey );
	}
	
	public String getUrlSolicitarAutorizacao() {
		return String.format(solicitarAutorizacao, appId, appKey );
	}
	
	public String getUrlDirecionarAutorizacao(String codigoAutorizacao ) {
		return String.format(direcionarAutorizacao, codigoAutorizacao );
	}
	
	public String getUrlConsultarAutorizacao(String codigoNotificacao ) {
		return String.format(consultarAutorizacao, codigoNotificacao , appId, appKey);
	}
	
	
	public String getUrlMeiosPagamento(String idSessao, Double valor) {
		return String.format(meiosPagamento, NumeroUtil.formataDoubleComDuasCasasDecimais(valor), idSessao );
	}
	

	
	public String getUrlOpcoesParcelamento(String idSessao, Double valor, String bandeiraCartao, Long maxParcelasSemJuros) {
		return String.format(opcoesParcelamento, idSessao, NumeroUtil.formataDoubleComDuasCasasDecimais(valor), bandeiraCartao, maxParcelasSemJuros );
	}
	
	public String getUrlCheckoutTransparenteCR() {
		return String.format(checkoutPagamentoCartaoCredito, appId, appKey );
	}
	
	public String getUrlCheckoutTransparenteBoleto() {
		return String.format(checkoutPagamentoBoleto, appId, appKey );
	}
	*/
	
}
