package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.CadastrarTipoPlanoCmd;
import br.com.cartaoamigo.cmd.GetTipoPlanosCmd;
import br.com.cartaoamigo.to.TipoPlanoTO;

@RestController
@RequestMapping(value = "tipoplanos")
public class TipoPlanoService {

	@Autowired private GetTipoPlanosCmd getCmd;
	@Autowired private CadastrarTipoPlanoCmd cadastrarCmd;
	@GetMapping(path = "/{idTipoPlano}", produces = MediaType.APPLICATION_JSON_VALUE )
	public TipoPlanoTO findById(@PathVariable(name = "idTipoPlano") Long idTipoPlano) {
		return getCmd.findByIdTipoPlano(idTipoPlano);
	}
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<TipoPlanoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/ativos", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<TipoPlanoTO> findAllByStatusA() {
		return getCmd.findAllAtivos();
	}
	
	@PostMapping(path = "")
	public TipoPlanoTO salvar(@RequestBody 	TipoPlanoTO to) {
		return cadastrarCmd.cadastrar(to);
	}
}
