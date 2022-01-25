package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPagarMeTO;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPlanoTO;


@Component
public class GetAssinaturasPlanoRecorrenciaPagarmeCmd {

	@Autowired private AssinaturaPlanoRecorrenciaService service;
	
	public List<AssinaturaPlanoTO> listarAssinaturasCliente(String idCliente) {
		try {
			AssinaturaPagarMeTO assinaturaPagarMeTO = service.listarAssinaturasCliente(idCliente);
			return assinaturaPagarMeTO.getData();
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao obter as assinaturas do cliente: " + e.getMessage());
		}
	}
	

}
