package br.com.cartaoamigo.ws.pagarme.cartao;

import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.ListaCartoesClienteTO;

public interface CartaoClienteRecorrenciaService {
	
	CartaoClienteTO excluirCartao(String idCliente, String idCartao) throws Exception ; 
	
	ListaCartoesClienteTO listarCartoes(String idCliente) throws Exception ;
	
	CartaoClienteTO getCartao(String idCliente, String idCartao) throws Exception ;
	
	CriarCartaoClienteTO criarCartao(CriarCartaoClienteTO cartaoTO, String idCliente) throws Exception ;
	
	CriarCartaoClienteTO editarCartao(CriarCartaoClienteTO cartaoTO, String idCliente, String idCartao) throws Exception ;
}
