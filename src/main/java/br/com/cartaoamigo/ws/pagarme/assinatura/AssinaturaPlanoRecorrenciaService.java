package br.com.cartaoamigo.ws.pagarme.assinatura;

import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPagarMeTO;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.CancelarAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.EditarCartaoAssinaturaPagarmeTO;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;

public interface AssinaturaPlanoRecorrenciaService {
	
	RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(NovaAssinaturaPlanoTO assinaturaTO) throws Exception ;
	RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(NovaAssinaturaPlanoTO assinaturaTO) throws Exception ;
	RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(String idAssinatura, CancelarAssinaturaPlanoTO cancelarTO)  throws Exception ;
	AssinaturaPagarMeTO listarAssinaturasCliente(String idCliente) throws Exception ;	
	AssinaturaPlanoTO editarCartaoAssinatura(String idAssinatura, EditarCartaoAssinaturaPagarmeTO cartaoTO)  throws Exception ;
	
}
