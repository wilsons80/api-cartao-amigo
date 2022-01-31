package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.to.ParansTokenCartaoPagarmeTO;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.TokenCartaoTO;
import br.com.cartaoamigo.ws.pagarme.tokencartao.TokenCartaoRecorrenciaService;


@Component
public class GetTokenCartaoRecorrenciaPagarmeCmd {

	@Autowired private TokenCartaoRecorrenciaService service;
	@Autowired private GetBandeiraCartaoRecorrenciaPagarmeCmd getBandeiraCartaoRecorrenciaPagarmeCmd;
	
	public TokenCartaoTO getTokenCartao(ParansTokenCartaoPagarmeTO tokenCartaoTO) {
		try {
			// buscar a bandeira do BIN do cartão
			BandeiraCartaoTO bandeiraCartaoTO = getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(tokenCartaoTO.getNumeroCartao());
			if(Objects.isNull(bandeiraCartaoTO) || Objects.isNull(bandeiraCartaoTO.getBrand())) {
				throw new PagarmeException("Não identificamos a bandeira do cartão.");
			}
			
			return service.getTokenCartao(tokenCartaoTO.getNumeroCartao(),
					                      tokenCartaoTO.getNomeImpresso(),
					                      bandeiraCartaoTO.getBrand(), 
					                      tokenCartaoTO.getCvv(), 
					                      tokenCartaoTO.getMesVencimentoCartao(), 
					                      tokenCartaoTO.getAnoVencimentoCartao());
		} catch (Exception e) {
			throw new PagarmeException("Erro ao obter o token do cartão, tente novamente mais tarde.");
		}
	}

}
