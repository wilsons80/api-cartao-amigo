package br.com.cartaoamigo.service.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.SalvarNotificacaoTransacaoCmd;
import br.com.cartaoamigo.cmd.gatewaypagamento.GetNotificacaoTransacaoCmd;
import br.com.cartaoamigo.to.NotificacaoTransacaoTO;
import br.com.cartaoamigo.ws.pagseguro.to.NotificacaoTransacaoGatewayTO;

@RestController
@RequestMapping(value = "pagseguro/split/notificacao")
public class NotificacaoTransacaoService {
	
	@Autowired private GetNotificacaoTransacaoCmd getCmd;
	@Autowired private SalvarNotificacaoTransacaoCmd salvarCmd;
	@Autowired private GetNotificacaoTransacaoCmd getNotificacaoTransacaoCmd;
	
	/**
	 * Endpoint usado para capturar o retorno das transações no Pagseguro.
	 * @param paramMap
	 */
	@PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void salvarNotificacao(@RequestParam MultiValueMap<String,String> paramMap) {
		salvarCmd.salvar(paramMap);
	}
	
	@GetMapping(path = "/{numeroTransacao}", produces = MediaType.APPLICATION_JSON_VALUE )
	public NotificacaoTransacaoTO findByNumeroTransacao(@PathVariable(name = "numeroTransacao") String numeroTransacao) {
		return getCmd.findByNumerotransacao(numeroTransacao);
	}
	
	@GetMapping(path = "/consultar/notificacao/{codigoNotificacao}", produces = MediaType.APPLICATION_JSON_VALUE )
	public NotificacaoTransacaoGatewayTO getNotificacacao(@PathVariable(name = "codigoNotificacao") String codigoNotificacao) {
		 return getNotificacaoTransacaoCmd.getNotificacaoByPagSeguro(codigoNotificacao);
	}
	
	
}
