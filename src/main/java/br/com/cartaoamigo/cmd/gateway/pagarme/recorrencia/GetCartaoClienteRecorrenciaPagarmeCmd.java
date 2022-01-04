package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.cartao.CartaoClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;


@Component
public class GetCartaoClienteRecorrenciaPagarmeCmd {

	@Autowired private CartaoClienteRecorrenciaService service;
	
	public List<CartaoClienteTO> getCartoesCliente(String idCliente) {
		try {
			return service.listarCartoes(idCliente);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter os cartões: " + e.getMessage());
		}
	}
	
	public CartaoClienteTO getCartaoCliente(String idCliente, String idCartao) {
		try {
			return service.getCartao(idCliente, idCartao);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter o cartão: " + e.getMessage());
		}
	}
	

}
