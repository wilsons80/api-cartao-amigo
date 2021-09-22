package br.com.cartaoamigo.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import br.com.cartaoamigo.cmd.AlterarUsuarioCmd;
import br.com.cartaoamigo.cmd.CadastrarUsuarioSistemaCmd;
import br.com.cartaoamigo.cmd.ExcluirUsuarioCmd;
import br.com.cartaoamigo.cmd.GetUsuarioCmd;
import br.com.cartaoamigo.dao.dto.UsuarioComboDTO;
import br.com.cartaoamigo.dao.dto.UsuarioDTO;
import br.com.cartaoamigo.to.UsuariosTO;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioService {

	@Autowired private GetUsuarioCmd getCmd;
	@Autowired private ExcluirUsuarioCmd excluirCmd;
	@Autowired private AlterarUsuarioCmd alterarCmd;
	@Autowired private CadastrarUsuarioSistemaCmd cadastrarCmd;	
	
	
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsuariosTO getById(@PathVariable(name = "id") Long id) {
		return getCmd.getTOById(id);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuarioDTO> getAllFilter(@RequestParam(name = "idUsuario", required = false) Long idUsuario,
								         @RequestParam(name = "dataInicioCadastro", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioCadastro,
		                                 @RequestParam(name = "dataFimCadastro", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimCadastro,
		                                 @RequestParam(name = "ativo", required = false) String ativo
		                                 ) {
		return getCmd.getAllFilterUsuario(idUsuario, dataInicioCadastro, dataFimCadastro, ativo);
	}
	

	@GetMapping(path = "/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuarioComboDTO> getAllCombo() {
		return getCmd.getAllCombo();
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuariosTO> getAll() {
		return getCmd.getAll();
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuariosTO cadastrar(@RequestBody UsuariosTO param) {
		return cadastrarCmd.cadastrar(param);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuariosTO alterar(@RequestBody UsuariosTO param) {
		return alterarCmd.alterar(param);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}	
}
