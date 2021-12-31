package br.com.cartaoamigo.service.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.SalvarClienteRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.ws.pagarme.to.ClientePagarMeTO;


@RestController
@RequestMapping(value = "pagarme/recorrencia/clientes")
public class ClienteRecorrenciaPagarmeService {
	
	@Autowired private SalvarClienteRecorrenciaPagarmeCmd salvarCmd;
	
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientePagarMeTO salvar(@RequestBody ClientePagarMeTO to) {
		return salvarCmd.salvar(to);
	}

	@PostMapping(path = "/salvar/base", produces = MediaType.APPLICATION_JSON_VALUE)
	public void salvarBase() {
		salvarCmd.salvarBase();
	}
}
