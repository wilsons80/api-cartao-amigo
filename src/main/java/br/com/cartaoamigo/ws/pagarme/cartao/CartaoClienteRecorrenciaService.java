package br.com.cartaoamigo.ws.pagarme.cartao;

import java.util.List;

import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;

public interface CartaoClienteRecorrenciaService {
	
	CartaoClienteTO excluirCartao(String idCliente, String idCartao) throws Exception ; 
	
	List<CartaoClienteTO> listarCartoes(String idCliente) throws Exception ;
	
	CartaoClienteTO getCartao(String idCliente, String idCartao) throws Exception ;
	
	CriarCartaoClienteTO criarCarto(CriarCartaoClienteTO cartaoTO, String idCliente) throws Exception ;
}
