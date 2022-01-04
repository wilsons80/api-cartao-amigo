package br.com.cartaoamigo.ws.pagarme.assinatura;

import java.util.List;

import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;

public interface AssinaturaPlanoRecorrenciaService {
	
	RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(NovaAssinaturaPlanoTO assinaturaTO) throws Exception ;
	RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(NovaAssinaturaPlanoTO assinaturaTO) throws Exception ;
	RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(String idAssinatura)  throws Exception ;
	List<AssinaturaPlanoTO> listarAssinaturasCliente(String idCliente) throws Exception ;
	
}
