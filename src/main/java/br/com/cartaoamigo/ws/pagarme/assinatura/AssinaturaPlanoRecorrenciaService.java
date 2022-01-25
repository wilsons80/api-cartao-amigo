package br.com.cartaoamigo.ws.pagarme.assinatura;

import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPagarMeTO;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;

public interface AssinaturaPlanoRecorrenciaService {
	
	RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(NovaAssinaturaPlanoTO assinaturaTO) throws Exception ;
	RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(NovaAssinaturaPlanoTO assinaturaTO) throws Exception ;
	RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(String idAssinatura)  throws Exception ;
	AssinaturaPagarMeTO listarAssinaturasCliente(String idCliente) throws Exception ;
	
}
