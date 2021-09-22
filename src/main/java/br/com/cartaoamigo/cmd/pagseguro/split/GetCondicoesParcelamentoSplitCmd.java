package br.com.cartaoamigo.cmd.pagseguro.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.split.condicoesparcelamento.CondicoesParcelamentoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.CondicaoParcelamentoTO;

@Component
public class GetCondicoesParcelamentoSplitCmd {

	@Autowired private CondicoesParcelamentoSplitService service;

	public CondicaoParcelamentoTO getCondicoesParcelamento(String idSessao, Double valor, String bandeiraCartao, Long maxParcelasSemJuros) {
		try {
			return service.getCondicoesParcelamento(idSessao, valor, bandeiraCartao, maxParcelasSemJuros);
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter as condições de parcelamento: " + e.getMessage());
		}
	}

}
