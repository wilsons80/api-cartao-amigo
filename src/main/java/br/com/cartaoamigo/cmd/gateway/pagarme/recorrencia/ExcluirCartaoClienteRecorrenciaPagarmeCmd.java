package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.cartao.CartaoClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;


@Component
public class ExcluirCartaoClienteRecorrenciaPagarmeCmd {

	@Autowired private CartaoClienteRecorrenciaService service;
	
	
	public CartaoClienteTO excluir(String idCliente, String idCartao) {
		try {
			return service.excluirCartao(idCliente, idCartao);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao excluir o cartão: " + e.getMessage());
		}
	}

}
