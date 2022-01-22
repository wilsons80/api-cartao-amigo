package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.CancelarAssinaturaCmd;
import br.com.cartaoamigo.cmd.GetAssinaturasCmd;
import br.com.cartaoamigo.to.AssinaturasTO;

@RestController
@RequestMapping(value = "assinaturas")
public class AssinaturasService {

	@Autowired private GetAssinaturasCmd getCmd;
	@Autowired private CancelarAssinaturaCmd cancelarAssinaturaCmd;
	
	@GetMapping(path = "/{codigoPagarme}", produces = MediaType.APPLICATION_JSON_VALUE )
	public AssinaturasTO findById(@PathVariable(name = "codigoPagarme") String codigoPagarme) {
		return getCmd.getAssinaturaTOCodigoPagarMe(codigoPagarme);
	}
	
	@GetMapping(path = "/titular/{idTitular}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<AssinaturasTO> getAll(@PathVariable(name = "idTitular") Long idTitular) {
		return getCmd.getAllAssinaturasTitular(idTitular);
	}
	
	@GetMapping(path = "/ativas/titular/{idTitular}", produces = MediaType.APPLICATION_JSON_VALUE )
	public AssinaturasTO getAtivaByIdTitular(@PathVariable(name = "idTitular") Long idTitular) {
		return getCmd.findAtivaByIdTitular(idTitular);
	}

	@DeleteMapping(path = "{codigoAssinaturaPagarme}", produces = MediaType.APPLICATION_JSON_VALUE )
	public AssinaturasTO cancelarAssinatura(@PathVariable(name = "codigoAssinaturaPagarme") String codigoAssinaturaPagarme) {
		return cancelarAssinaturaCmd.cancelarAssinatura(codigoAssinaturaPagarme);
	}
}
