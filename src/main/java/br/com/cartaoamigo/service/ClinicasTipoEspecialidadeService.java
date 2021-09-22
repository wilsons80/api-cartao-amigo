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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.ExcluirClinicasTipoEspecialidadeCmd;
import br.com.cartaoamigo.cmd.GetClinicasTipoEspecialidadeCmd;
import br.com.cartaoamigo.cmd.GetFilterClinicaTipoEspecialidadeCmd;
import br.com.cartaoamigo.cmd.SalvarClinicasTipoEspecialidadeCmd;
import br.com.cartaoamigo.dao.dto.ClinicaTipoEspecialidadeDTO;
import br.com.cartaoamigo.to.ClinicasTipoEspecialidadesTO;

@RestController
@RequestMapping(value = "clinicastipoespecialidade")
public class ClinicasTipoEspecialidadeService {

	@Autowired private GetClinicasTipoEspecialidadeCmd getCmd;
	@Autowired private SalvarClinicasTipoEspecialidadeCmd salvarCmd;
	@Autowired private ExcluirClinicasTipoEspecialidadeCmd excluirCmd;
	@Autowired private GetFilterClinicaTipoEspecialidadeCmd getFilterClinicaTipoEspecialidadeCmd;
	
	
	@GetMapping(path = "/{idClinicaTipoEspecialidade}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ClinicasTipoEspecialidadesTO getById(@PathVariable(name = "idClinicaTipoEspecialidade") Long id) {
		return getCmd.getById(id);
	}
	
	@GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClinicaTipoEspecialidadeDTO> getAllFilter(@RequestParam(name = "cidade", required = false) String cidade,
						                                  @RequestParam(name = "uf", required = false) String uf,
						                                  @RequestParam(name = "idTipoEspecialidade", required = false) Long idTipoEspecialidade
						                                 ) {
		return getFilterClinicaTipoEspecialidadeCmd.getAllFilterClinicaTipoEspecialidade(cidade, uf, idTipoEspecialidade);
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicasTipoEspecialidadesTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/clinica/{idClinica}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicasTipoEspecialidadesTO> getAllPorClinica(@PathVariable(name = "idClinica") Long idClinica) {
		return getCmd.getAllByClinica(idClinica);
	}
	
	@GetMapping(path = "/tipoEspecialidade/{idTipoEspecialidade}", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ClinicasTipoEspecialidadesTO> findAllByTipoEspecialidade(@PathVariable(name = "idTipoEspecialidade") Long idTipoEspecialidade){
		return getCmd.findAllByTipoEspecialidade(idTipoEspecialidade);
	}
	
	@DeleteMapping(path = "/{idClinicaTipoEspecialidade}", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletar(@PathVariable(name = "idClinicaTipoEspecialidade") Long idClinicaTipoEspecialidade) {
		excluirCmd.excluir(idClinicaTipoEspecialidade);
	}
	
	@PostMapping(path = "/clinica/{idClinica}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClinicasTipoEspecialidadesTO> salvar(@PathVariable(name = "idClinica") Long idClinica,
			                                         @RequestBody List<ClinicasTipoEspecialidadesTO> listaTO) {
		return salvarCmd.salvar(idClinica, listaTO);
	}

}
