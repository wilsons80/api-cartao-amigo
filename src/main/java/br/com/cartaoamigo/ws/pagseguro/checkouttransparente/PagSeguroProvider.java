package br.com.cartaoamigo.ws.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagSeguroProvider {
	
	@Value("${pagseguro.email}")                     private String emailPagSeguro;
	@Value("${pagseguro.token}")                     private String tokenPagSeguro;
	
	@Value("${pagseguro.checkouttransparente.urlsession}")                private String urlSession;
	@Value("${pagseguro.checkouttransparente.urlpagsegurodirectpayment}") private String urlPagSeguroDirectPayment;
	@Value("${pagseguro.checkouttransparente.urltransacao}")              private String urlTransacao;
	@Value("${pagseguro.checkouttransparente.urlBrand}")                  private String urlBandeira;
	@Value("${pagseguro.checkouttransparente.urlPayment-methods}")        private String urlMetodosPagamento;
	@Value("${pagseguro.checkouttransparente.urlTokenCartao}")            private String urlTokenCartao;
	@Value("${pagseguro.checkouttransparente.urlInstallments}")           private String urlCondicoesParcelamento;
	@Value("${pagseguro.checkouttransparente.urlCheckoutTransparenteCR}") private String urlCheckoutTransparenteCR;
	
	
	public String getUrlCheckoutTransparenteCR() {
		return String.format(urlCheckoutTransparenteCR, emailPagSeguro, tokenPagSeguro );
	}
	
	
	public String getUrlCondicoesParcelamento(String idSessao, Double valor, String bandeiraCartao, Long maxParcelasSemJuros) {
		return String.format(urlCondicoesParcelamento, idSessao, valor, bandeiraCartao, maxParcelasSemJuros );
	}
	
	public String getUrlTokenCartao() {
		return urlTokenCartao;
	}
	
	public String getUrlMetodosPagamento(String idSessao, Double valor) {
		return String.format(urlMetodosPagamento, valor, idSessao );
	}
	
	public String getUrlBandeira(String idSessao, String binCartao) {
		return String.format(urlBandeira, idSessao, binCartao );
	}
	
	public String getUrlSession() {
		return String.format(urlSession, emailPagSeguro, tokenPagSeguro );
	}
	
	public String getUrlPagSeguroDirectPayment() {
		return urlPagSeguroDirectPayment;
	}

	public String getUrlTransacao() {
		return urlTransacao;
	}

}
