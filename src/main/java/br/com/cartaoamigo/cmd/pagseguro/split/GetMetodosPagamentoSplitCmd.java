package br.com.cartaoamigo.cmd.pagseguro.split;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.ws.pagseguro.split.meiopagamento.MetodosPagamentoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.MetodosPagamentoTO;
import br.com.cartaoamigo.ws.pagseguro.to.PaymentMethodsTO;

@Component
public class GetMetodosPagamentoSplitCmd {
	@Value("${pagseguro.urlImagem}") private String urlImagemPagSeguro;
	@Autowired private MetodosPagamentoSplitService service;
	
	private List<String> tipos = Arrays.asList("CREDIT_CARD", "BOLETO");
	
	
	public MetodosPagamentoTO get(String idSessao, Double valor) {
		try {
			MetodosPagamentoTO metodosPagamento = service.getMetodosPagamento(idSessao, valor);
			metodosPagamento.getPaymentMethod().stream().sorted(Comparator.comparing(PaymentMethodsTO::getCode));
			metodosPagamento.getPaymentMethod().removeIf(p -> !tipos.contains(p.getName()));
			
			metodosPagamento.getPaymentMethod().forEach(pm -> {
				pm.getOptions().getOption().forEach(op -> {
					op.getImages().getImage().forEach(img -> img.setPath(urlImagemPagSeguro + img.getPath()));
				});
			});
			
			return metodosPagamento;
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao obter os métodos de pagamento (SPLIT): " + e.getMessage());
		}
	}

}
