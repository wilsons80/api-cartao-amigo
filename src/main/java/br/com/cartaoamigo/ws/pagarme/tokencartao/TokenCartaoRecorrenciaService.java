package br.com.cartaoamigo.ws.pagarme.tokencartao;

import br.com.cartaoamigo.ws.pagarme.to.TokenCartaoTO;

public interface TokenCartaoRecorrenciaService {
	
	
	/**
	 * 
	 * @param numeroCartao = Número completo do cartão sem espaços ou pontos
	 * @param nomeImpresso = Nome impresso no cartão
	 * @param bandeiraCartao
	 * @param cvv  = Código de segurança do cartão
	 * @param mesVencimentoCartao = 2 dígitos
	 * @param anoVencimentoCartao = 4 dígitos
	 * @return
	 */
	TokenCartaoTO getTokenCartao(String numeroCartao,
					            String nomeImpresso, 
					            String bandeiraCartao, 
					            String cvv, 
					            String mesVencimentoCartao, 
					            String anoVencimentoCartao) throws Exception ; 

}
