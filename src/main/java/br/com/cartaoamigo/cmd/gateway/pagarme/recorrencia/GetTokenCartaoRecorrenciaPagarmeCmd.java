package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.to.ParansTokenCartaoPagarmeTO;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.TokenCartaoTO;
import br.com.cartaoamigo.ws.pagarme.tokencartao.TokenCartaoRecorrenciaServiceImpl;


@Component
public class GetTokenCartaoRecorrenciaPagarmeCmd {

	@Autowired private TokenCartaoRecorrenciaServiceImpl service;
	@Autowired private GetBandeiraCartaoRecorrenciaPagarmeCmd getBandeiraCartaoRecorrenciaPagarmeCmd;
	
	public TokenCartaoTO getTokenCartao(ParansTokenCartaoPagarmeTO tokenCartaoTO) {
		try {
			// buscar a bandeira do BIN do cartão
			BandeiraCartaoTO bandeiraCartaoTO = getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(tokenCartaoTO.getNumeroCartao());
			
			return service.getTokenCartao(tokenCartaoTO.getNumeroCartao(),
					                      tokenCartaoTO.getNomeImpresso(),
					                      bandeiraCartaoTO.getBrand(), 
					                      tokenCartaoTO.getCvv(), 
					                      tokenCartaoTO.getMesVencimentoCartao(), 
					                      tokenCartaoTO.getAnoVencimentoCartao());
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter o token do cartão da transação: " + e.getMessage());
		}
	}

}
