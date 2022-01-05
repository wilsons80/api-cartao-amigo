package br.com.cartaoamigo.ws.pagarme.cartao;

import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.ListaCartoesClienteTO;

public interface CartaoClienteRecorrenciaService {
	
	CartaoClienteTO excluirCartao(String idCliente, String idCartao) throws Exception ; 
	
	ListaCartoesClienteTO listarCartoes(String idCliente) throws Exception ;
	
	CartaoClienteTO getCartao(String idCliente, String idCartao) throws Exception ;
	
	CriarCartaoClienteTO criarCarto(CriarCartaoClienteTO cartaoTO, String idCliente) throws Exception ;
}
