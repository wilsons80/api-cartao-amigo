package br.com.cartaoamigo.cmd.gateway.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.to.ParansTokenCartaoPagSeguroTO;
import br.com.cartaoamigo.ws.pagseguro.split.tokencartao.TokenCartaoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.TokenCartaoTO;


@Component
public class GetTokenCartaoSplitCmd {

	@Autowired private TokenCartaoSplitService service;

	public TokenCartaoTO getTokenCartao(ParansTokenCartaoPagSeguroTO tokenCartaoTO) {
		try {
			return service.getTokenCartao(tokenCartaoTO.getIdSessao(), 
					                      tokenCartaoTO.getValor(), 
					                      tokenCartaoTO.getNumeroCartao(), 
					                      tokenCartaoTO.getBandeiraCartao(), 
					                      tokenCartaoTO.getCvv(), 
					                      tokenCartaoTO.getMesVencimentoCartao(), 
					                      tokenCartaoTO.getAnoVencimentoCartao());
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter o token do cartão da transação: " + e.getMessage());
		}
	}

}
