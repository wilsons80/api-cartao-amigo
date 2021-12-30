package br.com.cartaoamigo.cmd.gateway.pagseguro.checkouttransparente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.condicoesparcelamento.CondicoesParcelamentoService;
import br.com.cartaoamigo.ws.pagseguro.to.CondicoesParcelamentoBandeirasTO;

@Component
public class GetCondicoesParcelamentoCmd {

	@Autowired private CondicoesParcelamentoService service;

	public CondicoesParcelamentoBandeirasTO getCondicoesParcelamento(String idSessao, Double valor, String bandeiraCartao, Long maxParcelasSemJuros) {
		try {
			return service.getCondicoesParcelamento(idSessao, valor, bandeiraCartao, maxParcelasSemJuros);
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter as condições de parcelamento: " + e.getMessage());
		}
	}

}
