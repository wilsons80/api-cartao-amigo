package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.ExcluirTipoEspecialidadeCmd;
import br.com.cartaoamigo.cmd.GetTipoEspecialidadeCmd;
import br.com.cartaoamigo.cmd.SalvarTipoEspecialidadeCmd;
import br.com.cartaoamigo.dao.dto.TipoEspecialidadeComboDTO;
import br.com.cartaoamigo.entity.TipoEspecialidade;
import br.com.cartaoamigo.to.TipoEspecialidadeTO;

@RestController
@RequestMapping(value = "tipoespecialidades")
public class TipoEspecialidadeService {
	
	@Autowired private GetTipoEspecialidadeCmd getCmd;
	@Autowired private ExcluirTipoEspecialidadeCmd excluirTipoEspecialidadeCmd;
	@Autowired private SalvarTipoEspecialidadeCmd salvarTipoEspecialidadeCmd;
	
	
	@GetMapping(path = "/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoEspecialidadeComboDTO> getAllCombo() {
		return getCmd.getAllCombo();
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoEspecialidadeTO> buscarTodos() {
		return getCmd.buscarTodos();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TipoEspecialidadeTO buscarPorId(@PathVariable(name = "id") 	Long idTipoEspecialidade ) {
		return getCmd.buscarPorId(idTipoEspecialidade);
	}
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long idTipoEspecialidade) {
		excluirTipoEspecialidadeCmd.excluir(idTipoEspecialidade);		
	}
	
	@PostMapping(path = "")
	public TipoEspecialidade salvar(@RequestBody TipoEspecialidadeTO to) {
		return salvarTipoEspecialidadeCmd.salvar(to);
	}
	
}
