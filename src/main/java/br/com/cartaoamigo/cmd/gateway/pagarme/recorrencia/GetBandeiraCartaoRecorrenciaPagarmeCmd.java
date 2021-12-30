package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.bandeira.BandeiraCartaoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;


@Component
public class GetBandeiraCartaoRecorrenciaPagarmeCmd {

	@Autowired private BandeiraCartaoRecorrenciaService bandeiraCartaoRecorrenciaService;
	
	public BandeiraCartaoTO getBandeira(String numeroCartao) {
		try {
			if(StringUtils.isEmpty(numeroCartao) || numeroCartao.length() < 6) {
				throw new PagarmeException("O bin do cartão informado está inválido.");
			}
			
			return bandeiraCartaoRecorrenciaService.getBandeiraCartao(numeroCartao.substring(0, 6));
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter a bandeira do cartão: " + e.getMessage());
		}
	}

}
