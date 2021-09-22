package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetParametrosCmd;
import br.com.cartaoamigo.cmd.GetTimeTokenExpiredCmd;
import br.com.cartaoamigo.to.ParametrosTO;

@RestController
@RequestMapping(value = "parametros")
public class ParametrosService {
	
	@Autowired private GetParametrosCmd getCmd;
	
	@Autowired private GetTimeTokenExpiredCmd getTimeTokenExpiredCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParametrosTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ParametrosTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getById(id);
	}

	@GetMapping(path = "/codigo/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ParametrosTO getByCodigo(@PathVariable(name = "codigo") String codigo) {
		return getCmd.getByCodigo(codigo);
	}
	
	@GetMapping(path = "/expiredToken")
	public Integer getTimeExpiredToken() {
		return getTimeTokenExpiredCmd.getTimeExpieredToken();
	}
	
}
