package br.com.cartaoamigo.cmd.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.bandeira.BandeiraService;
import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;

@Component
public class GetBandeirasCmd {

	@Autowired private BandeiraService bandeiraService;

	public BandeiraCartaoTO getBandeira(String idSessao, String binCartao) {
		try {
			return bandeiraService.getBandeira(idSessao, binCartao);
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter as bandeiras para pagamento: " + e.getMessage());
		}
	}

}
