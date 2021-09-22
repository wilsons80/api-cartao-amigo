package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetModulosCmd;
import br.com.cartaoamigo.to.ModuloTO;

@RestController
@RequestMapping(value = "modulo")
public class ModuloService {
	
	@Autowired private GetModulosCmd getModulosCmd; 
	

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getAllModulo() {
		return getModulosCmd.getAllModulo();
	}

	@GetMapping(path = "/com-acesso", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getComAcesso() {
		return getModulosCmd.getComAcesso();
	}
	
	@GetMapping(path = "/filhos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getAllModuloFilhos() {
		return getModulosCmd.getAllModuloFilhos();
	}
	
	@GetMapping(path = "/administrativo/filhos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ModuloTO> getAllAdministrativoModuloFilhos() {
		return getModulosCmd.getAllAdministrativoModuloFilhos();
	}
}
