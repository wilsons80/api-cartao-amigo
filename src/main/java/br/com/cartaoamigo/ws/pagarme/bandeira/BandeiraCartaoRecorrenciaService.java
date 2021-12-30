package br.com.cartaoamigo.ws.pagarme.bandeira;

import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;

public interface BandeiraCartaoRecorrenciaService {
	
	
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
	BandeiraCartaoTO getBandeiraCartao(String binCartao) throws Exception ; 

}
