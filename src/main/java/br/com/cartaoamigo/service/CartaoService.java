package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetCartaoCmd;
import br.com.cartaoamigo.to.CartaoTO;

@RestController
@RequestMapping(value = "cartao")
public class CartaoService {

	@Autowired private GetCartaoCmd getCmd;
	
	@GetMapping(path = "/numeroCartao/{numeroCartao}", produces = MediaType.APPLICATION_JSON_VALUE )
	public CartaoTO findByNumeroCartao(@PathVariable(name = "numeroCartao") String numeroCartao) {
		return getCmd.findByNumeroCartao(numeroCartao);
	}
	
	@GetMapping(path = "/titular/pessoa/{idPessoaFisica}", produces = MediaType.APPLICATION_JSON_VALUE )
	public CartaoTO getCartaoTitularByIdPessoaFisica(@PathVariable(name = "idPessoaFisica") Long idPessoaFisica) {
		return getCmd.getCartaoTitularByIdPessoaFisica(idPessoaFisica);
	}
	
	@GetMapping(path = "/dependente/pessoa/{idPessoaFisica}", produces = MediaType.APPLICATION_JSON_VALUE )
	public CartaoTO getCartaoDependenteByIdPessoaFisica(@PathVariable(name = "idPessoaFisica") Long idPessoaFisica) {
		return getCmd.getCartaoDependenteByIdPessoaFisica(idPessoaFisica);
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<CartaoTO> getAll() {
		return getCmd.getAll();
	}
	
	@GetMapping(path = "/ativos", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<CartaoTO> findAllByStatusA() {
		return getCmd.findAllAtivos();
	}
	
	@GetMapping(path = "/inativos", produces = MediaType.APPLICATION_JSON_VALUE )
	public List<CartaoTO> findAllByStatusI() {
		return getCmd.findAllInativos();
	}
	
}
