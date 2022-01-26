package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.cartao.CartaoClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.ListaCartoesClienteTO;


@Component
public class GetCartaoClienteRecorrenciaPagarmeCmd {

	@Autowired private CartaoClienteRecorrenciaService service;
	@Autowired private GetBandeiraCartaoRecorrenciaPagarmeCmd getBandeiraCartaoRecorrenciaPagarmeCmd;
	
	public List<CartaoClienteTO> getCartoesCliente(String idCliente) {
		try {
			ListaCartoesClienteTO listaCartoesClienteTO = service.listarCartoes(idCliente);
			Optional<List<CartaoClienteTO>> listaDeCartoes = Optional.ofNullable(listaCartoesClienteTO.getData());
			
			listaDeCartoes.ifPresent(cartoes -> {
				cartoes.forEach(cartao -> {
					// buscar a bandeira do BIN do cartão
					BandeiraCartaoTO bandeiraCartaoTO = getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(cartao.getFirst_six_digits());
					cartao.setBandeiraCartaoTO(bandeiraCartaoTO);
				});
			});
			
			return listaDeCartoes.orElseGet(null);
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter os cartões: " + e.getMessage());
		}
	}
	
	public CartaoClienteTO getCartaoCliente(String idCliente, String idCartao) {
		try {
			CartaoClienteTO cartaoClienteTO = service.getCartao(idCliente, idCartao);
			
			// buscar a bandeira do BIN do cartão
			BandeiraCartaoTO bandeiraCartaoTO = getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(cartaoClienteTO.getFirst_six_digits());
			cartaoClienteTO.setBandeiraCartaoTO(bandeiraCartaoTO);
			
			return cartaoClienteTO;
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter o cartão: " + e.getMessage());
		}
	}
	

}
