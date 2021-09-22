package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.tokencartao;

import br.com.cartaoamigo.ws.pagseguro.to.TokenCartaoTO;

public interface TokenCartaoService {
	
	
	/**
	 * 
	 * @param idSessao
	 * @param valor  = Valor com 2 casas decimais separado por ponto 10.00
	 * @param numeroCartao = Número completo do cartão sem espaços ou pontos
	 * @param bandeiraCartao
	 * @param cvv  = Código de segurança do cartão
	 * @param mesVencimentoCartao = 2 dígitos
	 * @param anoVencimentoCartao = 4 dígitos
	 * @return
	 */
	TokenCartaoTO getTokenCartao(String idSessao, 
			                     Double valor, 
			                     String numeroCartao, 
			                     String bandeiraCartao, 
			                     String cvv, 
			                     String mesVencimentoCartao, 
			                     String anoVencimentoCartao) throws Exception ; 

}
