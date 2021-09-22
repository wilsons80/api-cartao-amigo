package br.com.cartaoamigo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.ValidarCarteirinhaAssociadoCmd;
import br.com.cartaoamigo.to.ValidacaoCarteirinhaAssociadoTO;

@RestController
@RequestMapping(value = "validarcartao")
public class ValidarCarteirinhaAssociadoService {

	@Autowired private ValidarCarteirinhaAssociadoCmd validarCarteirinhaAssociadoCmd;

	
	@GetMapping(path = "/{idClinica}/{idTipoEspecialidade}/cartao/{nrCartao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ValidacaoCarteirinhaAssociadoTO validar(@PathVariable(name = "idClinica") Long idClinica, 
			                                       @PathVariable(name = "idTipoEspecialidade") Long idTipoEspecialidade,
			                                       @PathVariable(name = "nrCartao") String numeroCartao) {
		
		return validarCarteirinhaAssociadoCmd.getDadosCartao(idClinica, idTipoEspecialidade, numeroCartao);
	}
	
	
}
