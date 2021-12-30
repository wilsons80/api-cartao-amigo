package br.com.cartaoamigo.service.gateway.pagseguro.split;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.ExcluirAutorizacaoCmd;
import br.com.cartaoamigo.cmd.pagseguro.split.GetAutorizacaoSplitCmd;
import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoTO;

@RestController
@RequestMapping(value = "pagseguro/split/autorizacao")
public class AutorizacaoSplitService {
	
	@Autowired private GetAutorizacaoSplitCmd getCmd;
	@Autowired private ExcluirAutorizacaoCmd excluirCmd;
	
	
	@GetMapping(path = "/gerar", produces = MediaType.APPLICATION_JSON_VALUE)
	public AutorizacaoTO get() {
		return getCmd.getAutorizacao();
	}

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AutorizacaoTO> getAll() {
		return getCmd.getAll();
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public void deletar(@PathVariable(name = "id") Long id) {
		excluirCmd.excluir(id);
	}
	
	
}
