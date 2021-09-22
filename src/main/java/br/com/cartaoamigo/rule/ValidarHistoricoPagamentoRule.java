package br.com.cartaoamigo.rule;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;

@Component
public class ValidarHistoricoPagamentoRule {
	
	
	public void validar(HistoricoPagamentoTO to) {

		if(to.getNumeroTransacaoGatewayPagamento() == null) {
			throw new ParametroNaoInformadoException("O número de transação do Gateway de Pagamento não foi informado.");
		}
		if(to.getTitular() == null) {
			throw new ParametroNaoInformadoException("O titular não foi informado.");
		}
		if(to.getDtPagamentoPlanoContratado() == null) {
			throw new ParametroNaoInformadoException("Data do pagamento do plano não foi informada.");
		}
		if(to.getFormaPagamento() == null) {
			throw new ParametroNaoInformadoException("A forma de pagamento não foi informada.");
		}
		if(to.getTipoPlano() == null) {
			throw new ParametroNaoInformadoException("O tipo de plano não foi informado.");
		}
		if(to.getStatusTransacao() == null) {
			throw new ParametroNaoInformadoException("O status da transação não foi informado.");
		}
		
	}

}
