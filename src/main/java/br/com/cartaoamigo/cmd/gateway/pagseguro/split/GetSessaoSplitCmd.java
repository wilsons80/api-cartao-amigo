package br.com.cartaoamigo.cmd.gateway.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.split.sessao.SessaoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.SessaoTO;

@Component
public class GetSessaoSplitCmd {

	@Autowired private SessaoSplitService sessaoService;

	public SessaoTO getSessao() {
		try {
			return sessaoService.getSessao();
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter a sessão da transação: " + e.getMessage());
		}
	}

}
