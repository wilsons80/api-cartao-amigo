package br.com.cartaoamigo.ws.pagarme.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagarme.PagarmeRecorrenciaProvider;
import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.ListaCartoesClienteTO;

@Component
public class CartaoClienteRecorrenciaServiceImpl implements CartaoClienteRecorrenciaService{

	@Value("${pagarme.aplicacao.appId}") private String appId;
	
	@Autowired private PagarmeRecorrenciaProvider provider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public CartaoClienteTO excluirCartao(String idCliente, String idCartao) throws Exception {
		return httpRestUtil.delete(appId, null, provider.getUrlExcluirCartoesCliente(idCliente, idCartao), null , CartaoClienteTO.class);
	}

	@Override
	public ListaCartoesClienteTO listarCartoes(String idCliente) throws Exception {
		return httpRestUtil.get(appId, null, provider.getUrlListarCartoesCliente(idCliente), ListaCartoesClienteTO.class);
	}

	@Override
	public CartaoClienteTO getCartao(String idCliente, String idCartao) throws Exception {
		return httpRestUtil.get(appId, null, provider.getUrlListarCartaoCliente(idCliente, idCartao), CartaoClienteTO.class);
	}

	@Override
	public CriarCartaoClienteTO criarCartao(CriarCartaoClienteTO cartaoTO, String idCliente) throws Exception {
		return httpRestUtil.post(appId, null, provider.getUrlCriarCartaoCliente(idCliente), cartaoTO, CriarCartaoClienteTO.class);
	}
	
	@Override
	public CriarCartaoClienteTO editarCartao(CriarCartaoClienteTO cartaoTO, String idCliente, String idCartao) throws Exception {
		return httpRestUtil.put(appId, null, provider.getUrlEditarCartaoCliente(idCliente, idCartao), cartaoTO, CriarCartaoClienteTO.class);
	}
	
}
