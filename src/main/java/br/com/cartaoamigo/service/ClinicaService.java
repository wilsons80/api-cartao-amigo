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

import br.com.cartaoamigo.cmd.AlterarClinicaCmd;
import br.com.cartaoamigo.cmd.CadastrarClinicaCmd;
import br.com.cartaoamigo.cmd.ExcluirClinicaCmd;
import br.com.cartaoamigo.cmd.GetClinicasCmd;
import br.com.cartaoamigo.cmd.GetFilterClinicaCmd;
import br.com.cartaoamigo.dao.dto.ClinicaComboDTO;
import br.com.cartaoamigo.dao.dto.ClinicaDTO;
import br.com.cartaoamigo.to.ClinicaTO;

@RestController
@RequestMapping(value = "clinicas")
public class ClinicaService {

	@Autowired private GetClinicasCmd getClinicasCmd;
	@Autowired private CadastrarClinicaCmd salvarClinicaCmd;
	@Autowired private AlterarClinicaCmd alterarClinicaCmd;
	@Autowired private ExcluirClinicaCmd excluirClinicaCmd;
	@Autowired private GetFilterClinicaCmd getFilterClinicaCmd;
	
	
	@GetMapping(path = "/combo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClinicaComboDTO> getAllCombo() {
		return getClinicasCmd.getAllCombo();
	}
	
	@GetMapping(path = "/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ClinicaTO getById(@PathVariable(name = "idClinica") Long idClinica) {
		return getClinicasCmd.getById(idClinica);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClinicaDTO> getAllFilter(@RequestParam(name = "idClinica", required = false) Long idClinica,
		                                 @RequestParam(name = "ativo", required = false) String ativo,
		                                 @RequestParam(name = "dataInicioGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataInicioGerado,
	                                     @RequestParam(name = "dataFimGerado", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate dataFimGerado,
	                                     @RequestParam(name = "bairro", required = false) String bairro,
	                                     @RequestParam(name = "idTipoEspecialidade", required = false) Long idTipoEspecialidade
	                                    ) {
		return getFilterClinicaCmd.getAllFilterClinica(idClinica, ativo, dataInicioGerado, dataFimGerado, bairro, idTipoEspecialidade);
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> getAll() {
		return getClinicasCmd.getAll();
	}
	
	@GetMapping(path = "/bairro/{bairro}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> findAllByBairro(@PathVariable(name = "bairro") String bairro) {
		return getClinicasCmd.findAllByBairro(bairro);
	}
	
	@GetMapping(path = "/tipo/{idTipoEspecialidade}/especialidade", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> findAllByTipoEspecialidade(@PathVariable(name = "idTipoEspecialidade") Long idTipoEspecialidade) {
		return getClinicasCmd.findAllByTipoEspecialidade(idTipoEspecialidade);
	}
	
	@GetMapping(path = "/valor/associado/{valor}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> findAllByValorAssociado(@PathVariable(name = "valor") Double valor) {
		return getClinicasCmd.findAllByValorAssociado(valor);
	}
	
	@GetMapping(path = "/valor/particular/{valor}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> findAllByValorParticular(@PathVariable(name = "valor") Double valor) {
		return getClinicasCmd.findAllByValorParticular(valor);
	}
	
	@GetMapping(path = "/ativas", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> findAllByStatusA() {
		return getClinicasCmd.findAllAtivos();
	}
	
	@GetMapping(path = "/inativas", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicaTO> findAllByStatusI() {
		return getClinicasCmd.findAllInativos();
	}
		
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ClinicaTO salvar(@RequestBody ClinicaTO to) {
		return salvarClinicaCmd.salvar(to);
	}
	
	@PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ClinicaTO alterar(@RequestBody ClinicaTO to) {
		return alterarClinicaCmd.alterar(to);
	}
	
	@DeleteMapping(path = "/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletar(@PathVariable(name = "idClinica") Long idClinica) {
		excluirClinicaCmd.excluir(idClinica);
	}


}
