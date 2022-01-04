package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;


@Component
public class CriarAssinaturaPlanoRecorrenciaPagarmeCmd {

	@Autowired private AssinaturaPlanoRecorrenciaService service;
	
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(@RequestBody NovaAssinaturaPlanoTO assinaturaTO) {
		try {
			assinaturaTO.setPayment_method("credit_card");
			return service.criarAssinaturaCartao(assinaturaTO);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao criar a assinatura com cartão: " + e.getMessage());
		}
	}

	
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(@RequestBody NovaAssinaturaPlanoTO assinaturaTO) {
		try {
			assinaturaTO.setBoleto_due_days(5);
			assinaturaTO.setPayment_method("boleto");
			
			return service.criarAssinaturaBoleto(assinaturaTO);
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao criar a assinatura com boleto: " + e.getMessage());
		}
	}

}
