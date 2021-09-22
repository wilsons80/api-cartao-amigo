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

import br.com.cartaoamigo.cmd.AlterarMedicoEspecialidadesCmd;
import br.com.cartaoamigo.cmd.ExcluirMedicoEspecialidadesCmd;
import br.com.cartaoamigo.cmd.GetMedicoEspecialidadesCmd;
import br.com.cartaoamigo.cmd.SalvarMedicoEspecialidadesCmd;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;

@RestController
@RequestMapping(path = "medicoespecialidades")
public class MedicoEspecialidadesService {
	
	@Autowired private ExcluirMedicoEspecialidadesCmd excluirMedicoEspecialidadesCmd;
	@Autowired private SalvarMedicoEspecialidadesCmd salvarMedicoEspecialidadesCmd;
	@Autowired private AlterarMedicoEspecialidadesCmd alterarCmd;
	@Autowired private GetMedicoEspecialidadesCmd getCmd;
	
	
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") Long idMedicoEspecialidades) {
		excluirMedicoEspecialidadesCmd.excluir(idMedicoEspecialidades);
	}
	
	@PostMapping(path = "/medico/{idMedico}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MedicoEspecialidadesTO> cadastrar(@PathVariable(name = "idMedico") Long idMedico,
			                                      @RequestBody List<MedicoEspecialidadesTO> listaTO) {
		return salvarMedicoEspecialidadesCmd.salvarAll(listaTO, idMedico);
	}
	
	@PutMapping(path = "/medico/{idMedico}")
	public List<MedicoEspecialidadesTO> alterar(@PathVariable(name = "idMedico") Long idMedico, 
			                                    @RequestBody List<MedicoEspecialidadesTO> to) {
		return alterarCmd.alterarAll(to, idMedico);
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MedicoEspecialidadesTO> buscar() {
		return getCmd.buscarTodos();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicoEspecialidadesTO buscarPorId(@PathVariable(name = "id") Long idMedicoEspecialidades) {
		return getCmd.buscarPorId(idMedicoEspecialidades);
	}
}
