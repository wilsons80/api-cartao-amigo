package br.com.cartaoamigo.ws.pagarme.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.ClientePagarMeTO;

@Component
public class ClienteRecorrenciaServiceImpl implements ClienteRecorrenciaService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public ClientePagarMeTO criar(ClientePagarMeTO clienteTO) throws Exception {
		return httpRestUtil.post(appId, null, provider.getUrlCriarCliente(), clienteTO, ClientePagarMeTO.class);
	}
	
	@Override
	public ClientePagarMeTO editar(ClientePagarMeTO clienteTO) throws Exception {
		return httpRestUtil.post(appId, null, provider.getUrlEditarCliente(clienteTO.getId()), clienteTO, ClientePagarMeTO.class);
	}
	
}
