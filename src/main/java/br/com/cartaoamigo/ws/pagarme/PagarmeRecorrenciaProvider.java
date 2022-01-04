package br.com.cartaoamigo.ws.pagarme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagarmeRecorrenciaProvider {
	
	@Value("${pagarme.aplicacao.appId}")                                  private String appId;

	
	@Value("${pagarme.recorrencia.urlTokenCartao}")                        private String tokenCartao;
	@Value("${pagarme.recorrencia.bandeira.cartao}")                       private String bandeiraCartao;
	@Value("${pagarme.recorrencia.criar.cliente}")                         private String criarCliente;
	@Value("${pagarme.recorrencia.editar.cliente}")                        private String editarCliente;
	@Value("${pagarme.recorrencia.listar.cartoes}")                        private String listarCartoesCliente;
	@Value("${pagarme.recorrencia.listar.cartao}")                         private String listarCartaoCliente;
	@Value("${pagarme.recorrencia.excluir.cartao}")                        private String excluirCartaoCliente;
	@Value("${pagarme.recorrencia.criar.cartao}")                          private String criarCartaoCliente;
	@Value("${pagarme.recorrencia.assinatura}")                            private String assinaturaPlano;
	@Value("${pagarme.recorrencia.cancelar.assinatura}")                   private String cancelarAssinaturaPlano;
	@Value("${pagarme.recorrencia.listar.assinaturas}")                    private String listarAssinaturaCliente;
	@Value("${pagarme.recorrencia.listar.assinaturas.faturas}")            private String listarFaturasDaAssinaturaCliente;
	@Value("${pagarme.recorrencia.cobranca.fatura}")                       private String cobrancaFatura;
	
	
	public String getUrlBandeira(String binCartao) {
		return String.format(bandeiraCartao, binCartao );
	}
	
	public String getUrlTokenCartao() {
		return String.format(tokenCartao, appId );
	}
	
	public String getUrlCriarCliente() {
		return String.format(criarCliente, appId );
	}
	
	public String getUrlEditarCliente(String idCliente) {
		return String.format(editarCliente, idCliente );
	}

	public String getUrlListarCartoesCliente(String idCliente) {
		return String.format(listarCartoesCliente, idCliente );
	}
	
	public String getUrlListarCartaoCliente(String idCliente, String idCartao) {
		return String.format(listarCartaoCliente, idCliente, idCartao);
	}

	public String getUrlExcluirCartoesCliente(String idCliente, String idCartao) {
		return String.format(excluirCartaoCliente, idCliente , idCartao);
	}

	public String getUrlCriarCartaoCliente(String idCliente) {
		return String.format(criarCartaoCliente, idCliente);
	}

	public String getUrlAssinaturaPlano() {
		return String.format(assinaturaPlano);
	}

	public String getUrlCancelarAssinaturaPlano(String idAssinatura) {
		return String.format(cancelarAssinaturaPlano, idAssinatura);
	}
	
	public String getUrlListarAssinaturasCliente(String idCliente) {
		return String.format(listarAssinaturaCliente, idCliente);
	}
	
	public String getUrlListarFaturasAssinaturaCliente(String idCliente, String idAssinatura) {
		return String.format(listarFaturasDaAssinaturaCliente, idCliente, idAssinatura);
	}
	
	public String getUrlCobrancaFatura(String idCobranca) {
		return String.format(cobrancaFatura, idCobranca);
	}
	
	
}
