package br.com.cartaoamigo.cmd.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.split.bandeira.BandeiraCartaoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.BandeiraCartaoTO;

@Component
public class GetBandeirasSplitCmd {

	@Autowired private BandeiraCartaoSplitService bandeiraService;

	public BandeiraCartaoTO getBandeira(String idSessao, String binCartao) {
		try {
			return bandeiraService.getBandeira(idSessao, binCartao);
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter as bandeiras para pagamento: " + e.getMessage());
		}
	}

}
