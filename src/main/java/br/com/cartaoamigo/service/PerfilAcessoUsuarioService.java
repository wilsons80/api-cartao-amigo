package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.AlterarAcessoUsuarioCmd;
import br.com.cartaoamigo.cmd.CadastrarPerfilAcessoUsuarioCmd;
import br.com.cartaoamigo.cmd.GetAcessoCmd;
import br.com.cartaoamigo.cmd.GetPerfilAcessoUsuarioCmd;
import br.com.cartaoamigo.to.AcessoTO;
import br.com.cartaoamigo.to.DadosUsuarioComAcessoTO;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;

@RestController
@RequestMapping(value = "perfilacessousuario")
public class PerfilAcessoUsuarioService {
	
	@Autowired private CadastrarPerfilAcessoUsuarioCmd cadastrarAcessoUsuarioCmd;
	@Autowired private AlterarAcessoUsuarioCmd alterarAcessoUsuarioCmd;
	@Autowired private GetAcessoCmd getPerfilAcessoCmd;
	@Autowired private GetPerfilAcessoUsuarioCmd getPerfilAcessoUsuarioCmd;
	
	
	@PostMapping(path = "/gruposacesso", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoUsuarioTO> cadastrarAll(@RequestBody List<PerfilAcessoUsuarioTO> listaAcesso) {
		return cadastrarAcessoUsuarioCmd.cadastrarAll(listaAcesso);
	}
	
	@PutMapping(path = "/gruposacesso", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoUsuarioTO> alterar(@RequestBody List<PerfilAcessoUsuarioTO> listaAcesso) {
		return alterarAcessoUsuarioCmd.alterar(listaAcesso);
	}
	
	@GetMapping(path = "/gruposacesso/usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoUsuarioTO> getAllByUsuario(@PathVariable(name = "idUsuario") Long idUsuario) {
		return getPerfilAcessoUsuarioCmd.getAllTOByUsuario(idUsuario);
	}
	
	@GetMapping(path = "/logado", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PerfilAcessoUsuarioTO> getAllByUsuarioLogado() {
		return getPerfilAcessoUsuarioCmd.getAllByUsuarioLogado();
	}
	
	
	
	
	
	//Usado na tela de CONSULTA de acesso para um usuário específico
	@GetMapping(path = "/perfil/usuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DadosUsuarioComAcessoTO> getPerfilAcessoDoUsuarioLogado(@PathVariable(name = "idUsuario") Long idUsuario) {
		return getPerfilAcessoCmd.getPerfilAcessoDoUsuarioLogado(idUsuario);
	}
	
	
	//Retorna os acessos do módulo acessado no menu
	@GetMapping(path = "/modulo/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AcessoTO> getPerfilAcesso(@PathVariable(name = "nome") String nomeModulo) {
		return getPerfilAcessoCmd.getPerfilAcesso(nomeModulo);
	}

}
