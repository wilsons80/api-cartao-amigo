package br.com.cartaoamigo.ws.pagarme.cliente;

import br.com.cartaoamigo.ws.pagarme.to.ClientePagarMeTO;

public interface ClienteRecorrenciaService {
	
	ClientePagarMeTO criar(ClientePagarMeTO clienteTO) throws Exception ; 
	
	ClientePagarMeTO editar(ClientePagarMeTO clienteTO) throws Exception ;

}
