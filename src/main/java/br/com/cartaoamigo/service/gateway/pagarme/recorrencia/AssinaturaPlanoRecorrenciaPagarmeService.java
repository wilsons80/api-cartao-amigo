package br.com.cartaoamigo.service.gateway.pagarme.recorrencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.CancelarAssinaturaPlanoRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.CriarAssinaturaPlanoRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetAssinaturasPlanoRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCanceladaTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;


@RestController
@RequestMapping(value = "pagarme/recorrencia/assinaturas")
public class AssinaturaPlanoRecorrenciaPagarmeService {
	
	@Autowired private CriarAssinaturaPlanoRecorrenciaPagarmeCmd criarAssinaturaCmd;
	@Autowired private CancelarAssinaturaPlanoRecorrenciaPagarmeCmd cancelarAssinaturaCmd;
	@Autowired private GetAssinaturasPlanoRecorrenciaPagarmeCmd getAssinaturasCmd;
	
	@GetMapping(path = "/cliente/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AssinaturaPlanoTO> listarAssinaturasCliente(@PathVariable(name = "idCliente") String idCliente) {
		return getAssinaturasCmd.listarAssinaturasCliente(idCliente);
	}
	
	@GetMapping(path = "/vigente/cliente/{idCliente}")
	public boolean temAssinaturasVigente(@PathVariable(name = "idCliente") String idCliente) {
		return getAssinaturasCmd.temAssinaturaVigentePagarMe(idCliente);
	}
	
	@PostMapping(path = "/criar/cartao", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(@RequestBody NovaAssinaturaPlanoTO assinaturaTO) {
		return criarAssinaturaCmd.criarAssinaturaCartao(assinaturaTO);
	}
	
	@PostMapping(path = "/criar/boleto", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(@RequestBody NovaAssinaturaPlanoTO assinaturaTO) {
		return criarAssinaturaCmd.criarAssinaturaBoleto(assinaturaTO);
	}
	
	@DeleteMapping(path = "/{codigoAssinaturaPagarme}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetornoAssinaturaPlanoCanceladaTO cancelarAssinatura(@PathVariable(name = "codigoAssinaturaPagarme") String codigoAssinaturaPagarme) {
		return cancelarAssinaturaCmd.cancelarAssinatura(codigoAssinaturaPagarme);
	}
	
}
