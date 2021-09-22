package br.com.cartaoamigo.ws.pagseguro.split;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.infra.util.NumeroUtil;

@Component
public class PagSeguroSplitProvider {
	
	@Value("${pagseguro.email}")                                           private String emailPagSeguro;
	@Value("${pagseguro.token}")                                           private String tokenPagSeguro;
	
	@Value("${pagseguro.split.aplicacao.appId}")                           private String appId;
	@Value("${pagseguro.split.aplicacao.appKey}")                          private String appKey;

	@Value("${pagseguro.split.solicitar.autorizacao}")                     private String solicitarAutorizacao;
	@Value("${pagseguro.split.direct.autorizacao}")                        private String direcionarAutorizacao;
	@Value("${pagseguro.split.consultar.autorizacao}")                     private String consultarAutorizacao;
	@Value("${pagseguro.split.sessao}")                                    private String sessao;
	@Value("${pagseguro.split.meios.pagamento}")                           private String meiosPagamento;
	@Value("${pagseguro.split.bandeira.cartao}")                           private String bandeirasCartao;
	@Value("${pagseguro.split.urlTokenCartao}")                            private String tokenCartao;
	@Value("${pagseguro.split.opcoes.parcelamento}")                       private String opcoesParcelamento;
	@Value("${pagseguro.split.checkout.cartaocredito}")                    private String checkoutPagamentoCartaoCredito;
	@Value("${pagseguro.split.consultar.notificacao}")                     private String consultarNotificacao;
	@Value("${pagseguro.split.checkout.boleto}")                           private String checkoutPagamentoBoleto;
	
	
	
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
	
	public String getUrlSession() {
		return String.format(sessao, appId, appKey );
	}
	
	public String getUrlMeiosPagamento(String idSessao, Double valor) {
		return String.format(meiosPagamento, NumeroUtil.formataDoubleComDuasCasasDecimais(valor), idSessao );
	}
	
	public String getUrlBandeira(String idSessao, String binCartao) {
		return String.format(bandeirasCartao, idSessao, binCartao );
	}
	
	public String getUrlTokenCartao() {
		return String.format(tokenCartao, emailPagSeguro, tokenPagSeguro );
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

}
