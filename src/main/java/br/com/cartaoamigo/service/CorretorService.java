package br.com.cartaoamigo.service;

import java.time.LocalDate;
import java.util.List;

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

import br.com.cartaoamigo.cmd.AlterarCorretorCmd;
import br.com.cartaoamigo.cmd.CadastrarCorretorCmd;
import br.com.cartaoamigo.cmd.ExcluirCorretorCmd;
import br.com.cartaoamigo.cmd.GetCorretorCmd;
import br.com.cartaoamigo.dao.dto.CorretorComboDTO;
import br.com.cartaoamigo.dao.dto.CorretorDTO;
import br.com.cartaoamigo.to.CorretorTO;

@RestController
@RequestMapping(value = "corretores")
public class CorretorService {

	@Autowired private GetCorretorCmd getCmd;
	@Autowired private CadastrarCorretorCmd salvarCmd;
	@Autowired private AlterarCorretorCmd alterarCmd;
	@Autowired private ExcluirCorretorCmd excluirCmd;
	
	
	@GetMapping(path = "/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CorretorComboDTO> getAllCombo() {
		return getCmd.getAllCombo();
	}
	
	@GetMapping(path = "/{idCorretor}", produces = MediaType.APPLICATION_JSON_VALUE )
	public CorretorTO getById(@PathVariable(name = "idCorretor") Long idCorretor) {
		return getCmd.getById(idCorretor);
	}

	@GetMapping(path = "/token/{token}", produces = MediaType.APPLICATION_JSON_VALUE )
	public CorretorTO getByToken(@PathVariable(name = "token") String token) {
		return getCmd.getByToken(token);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CorretorDTO> getAllFilter(@RequestParam(name = "idCorretor", required = false) Long idCorretor,
			                              @RequestParam(name = "ativo", required = false) String ativo,
								          @RequestParam(name = "dataInicioCadastro", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioCadastro,
		                                  @RequestParam(name = "dataFimCadastro", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimCadastro
		                                  ) {
		return getCmd.getAllFilter(idCorretor, ativo, dataInicioCadastro, dataFimCadastro);
	}
	
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CorretorTO salvar(@RequestBody CorretorTO to) {
		return salvarCmd.salvar(to);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CorretorTO alterar(@RequestBody CorretorTO to) {
		return alterarCmd.alterar(to);
	}
	
	@DeleteMapping(path = "/{idCorretor}", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletar(@PathVariable(name = "idCorretor") Long idCorretor) {
		excluirCmd.excluir(idCorretor);
	}


}
