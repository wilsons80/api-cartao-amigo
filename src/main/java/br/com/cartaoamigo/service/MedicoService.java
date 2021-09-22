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

import br.com.cartaoamigo.cmd.ExcluirMedicoCmd;
import br.com.cartaoamigo.cmd.GetMedicoCmd;
import br.com.cartaoamigo.cmd.SalvarMedicoCmd;
import br.com.cartaoamigo.to.MedicoTO;

@RestController
@RequestMapping(value = "medicos")
public class MedicoService {
	
	@Autowired private GetMedicoCmd getCmd;
	@Autowired private ExcluirMedicoCmd excluirMedicoCmd;
	@Autowired private SalvarMedicoCmd salvarMedicoCmd;

	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MedicoTO> buscarTodos() {
		return getCmd.buscarTodos();
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicoTO buscarPorId(@PathVariable(name = "id") 	Long idMedico) {
		return getCmd.buscarPorId(idMedico);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public void excluir(@PathVariable(name = "id") 	Long idMedico) {
		excluirMedicoCmd.excluir(idMedico);
	}
	
	@PostMapping(path = "",  produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicoTO salvar(@RequestBody MedicoTO to) {
		return salvarMedicoCmd.salvar(to);
	}
}
