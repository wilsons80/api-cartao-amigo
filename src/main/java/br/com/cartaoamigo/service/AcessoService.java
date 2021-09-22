package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetAcessoCmd;
import br.com.cartaoamigo.to.AcessoTO;

@RestController
@RequestMapping(value = "acesso")
public class AcessoService {
	
	@Autowired private GetAcessoCmd getPerfilAcessoCmd;

	
	//Retorna os acesso do módulo acessado no menu
	@GetMapping(path = "/modulo/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcessoTO> getPerfilAcesso(@PathVariable(name = "nome") String nomeModulo) {
		return getPerfilAcessoCmd.getPerfilAcesso(nomeModulo);
	}

}
