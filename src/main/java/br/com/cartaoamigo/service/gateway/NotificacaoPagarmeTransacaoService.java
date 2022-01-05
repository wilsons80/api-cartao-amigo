package br.com.cartaoamigo.service.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.pagarme.SalvarNotificacaoPagarMeTransacaoCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetWebhookPagarmeCmd;
import br.com.cartaoamigo.cmd.gatewaypagamento.GetNotificacaoTransacaoCmd;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;
import br.com.cartaoamigo.to.pagarme.NotificacaoPagarmeTransacaoTO;
import br.com.cartaoamigo.ws.pagarme.to.WebHookPagarMeTO;
import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoGatewayTO;

@RestController
@RequestMapping(value = "pagarme/notificacao")
public class NotificacaoPagarmeTransacaoService {
	
	@Autowired private SalvarNotificacaoPagarMeTransacaoCmd salvarCmd;
	@Autowired private GetNotificacaoTransacaoCmd getNotificacaoTransacaoCmd;
	@Autowired private GetWebhookPagarmeCmd getWebhookPagarmeCmd;
	
	/**
	 * Endpoint usado para capturar o retorno das transações no pagarme.
	 * @param paramMap
	 */
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void salvarNotificacao(@RequestBody NotificacaoPagarmeTransacaoTO notificacao) {
		salvarCmd.salvar(notificacao);
	}
	
	@GetMapping(path = "/{numeroTransacao}", produces = MediaType.APPLICATION_JSON_VALUE )
	public NotificacaoTransacaoTO findByNumeroTransacao(@PathVariable(name = "numeroTransacao") String numeroTransacao) {
		return getNotificacaoTransacaoCmd.findByNumerotransacao(numeroTransacao);
	}
	
	@GetMapping(path = "/consultar/notificacao/{codigoNotificacao}", produces = MediaType.APPLICATION_JSON_VALUE )
	public NotificacaoTransacaoGatewayTO getNotificacacao(@PathVariable(name = "codigoNotificacao") String codigoNotificacao) {
		 return getNotificacaoTransacaoCmd.getNotificacaoByPagSeguro(codigoNotificacao);
	}
	
	@GetMapping(path = "/webhook/{idWebhook}", produces = MediaType.APPLICATION_JSON_VALUE )
	public WebHookPagarMeTO getWebhook(@PathVariable(name = "idWebhook") String idWebhook) {
		return getWebhookPagarmeCmd.getWebhook(idWebhook);
	}
}
