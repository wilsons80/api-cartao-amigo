package br.com.cartaoamigo.cmd.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.split.autorizacao.GetCodigoAutorizacaoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoCorretorTO;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoTO;

@Component
public class GetCodigoAutorizacaoCorretorSplitCmd {

	@Autowired private GetCodigoAutorizacaoSplitService autorizacaoService;

	public CodigoAutorizacaoTO obter(CodigoAutorizacaoCorretorTO dadosTO) {
		try {
			return autorizacaoService.getCodigoAutorizacao(dadosTO);
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter o código da autorização: " + e.getMessage());
		}
	}

}
