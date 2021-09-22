package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetHistoricoPagamentoCmd;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;

@RestController
@RequestMapping(value = "historicospagamentos")
public class HistoricoPagamentoService {
	
	@Autowired private GetHistoricoPagamentoCmd getCmd;
	
	
	@GetMapping(path = "/{idHistoricoPagamento}", produces = MediaType.APPLICATION_JSON_VALUE )
	public HistoricoPagamentoTO getById(@PathVariable(name = "idHistoricoPagamento") Long idHistoricoPagamento) {
		return getCmd.getTOById(idHistoricoPagamento);
	}
	
	@GetMapping(path = "/titular/{idTitular}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<HistoricoPagamentoTO> getAllById(@PathVariable(name = "idTitular") Long idTitular){
		return getCmd.getAllTOByIdTitular(idTitular);
	}

		
}
