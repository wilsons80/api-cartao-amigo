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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AlterarGrupoAcessoCmd;
import br.com.cartaoamigo.cmd.CadastrarGrupoAcessoCmd;
import br.com.cartaoamigo.cmd.ExcluirGrupoAcessoCmd;
import br.com.cartaoamigo.cmd.GetGrupoAcessoCmd;
import br.com.cartaoamigo.to.GrupoAcessoTO;

@RestController
@RequestMapping(value = "gruposacessos")
public class GrupoAcessoService {
	
	@Autowired private CadastrarGrupoAcessoCmd cadastrarCmd;
	@Autowired private AlterarGrupoAcessoCmd alterarCmd;
	
	@Autowired private GetGrupoAcessoCmd getCmd;	
	@Autowired private ExcluirGrupoAcessoCmd excluirCmd;
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcessoTO cadastrar(@RequestBody GrupoAcessoTO to) {
		return cadastrarCmd.cadastrar(to);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcessoTO alterar(@RequestBody GrupoAcessoTO to) {
		return alterarCmd.alterar(to);
	}
	
	@GetMapping(path = "/administrativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoAcessoTO> getAllGruposAdministrativos() {
		return getCmd.getAllGruposAdministrativos();
	}
	
	@GetMapping(path = "/{idGrupoAcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoAcessoTO getByID(@PathVariable(name = "idGrupoAcesso") Long idGrupoAcesso) {
		return getCmd.getById(idGrupoAcesso);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoAcessoTO> getAllFilter(@RequestParam(name = "idModulo", required = false) Long idModulo,
											@RequestParam(name = "nome", required = false) String nome) {
		return getCmd.getAllFilter(idModulo, nome);
	}
	
	@DeleteMapping(path = "/{idGrupoAcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void remover(@PathVariable(name = "idGrupoAcesso") Long idGrupoAcesso) {
		excluirCmd.excluir(idGrupoAcesso);
	}
}
