package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AlterarTipoCargoCmd;
import br.com.cartaoamigo.cmd.CadastrarTipoCargoCmd;
import br.com.cartaoamigo.cmd.ExcluirTipoCargoCmd;
import br.com.cartaoamigo.cmd.GetTipoCargoCmd;
import br.com.cartaoamigo.to.TipoCargoTO;

@RestController
@RequestMapping(value = "tipocargos")
public class TipoCargoService {

	@Autowired private GetTipoCargoCmd getCmd;
	@Autowired private CadastrarTipoCargoCmd cadastrarCmd;
	@Autowired private ExcluirTipoCargoCmd excluirCmd;
	@Autowired private AlterarTipoCargoCmd alterarCmd;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoCargoTO> getAll() {
		return getCmd.getAll();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TipoCargoTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TipoCargoTO cadastrar(@RequestBody TipoCargoTO unidade) {
		return cadastrarCmd.cadastrar(unidade);
	}

	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public TipoCargoTO alterar(@RequestBody TipoCargoTO to) {
		return alterarCmd.alterar(to);
	}

	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}


}
