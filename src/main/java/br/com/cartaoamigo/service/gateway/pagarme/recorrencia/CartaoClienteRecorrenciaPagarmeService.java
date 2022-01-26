package br.com.cartaoamigo.service.gateway.pagarme.recorrencia;

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

import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.CriarCartaoClienteRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.ExcluirCartaoClienteRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetBandeiraCartaoRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetCartaoClienteRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.GetTokenCartaoRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.to.ParansTokenCartaoPagarmeTO;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;
import br.com.cartaoamigo.ws.pagarme.to.TokenCartaoTO;


@RestController
@RequestMapping(value = "pagarme/recorrencia/clientes/cartoes")
public class CartaoClienteRecorrenciaPagarmeService {
	
	@Autowired private GetTokenCartaoRecorrenciaPagarmeCmd getCmd;
	@Autowired private ExcluirCartaoClienteRecorrenciaPagarmeCmd excluirCmd;
	@Autowired private GetCartaoClienteRecorrenciaPagarmeCmd getCartaoCmd;
	@Autowired private CriarCartaoClienteRecorrenciaPagarmeCmd criarCartaoCmd;
	@Autowired private GetBandeiraCartaoRecorrenciaPagarmeCmd getBandeiraCartaoRecorrenciaPagarmeCmd;
	
	@PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenCartaoTO getTokenCartao(@RequestBody ParansTokenCartaoPagarmeTO tokenCartaoTO) {
		return getCmd.getTokenCartao(tokenCartaoTO);
	}
	
	@DeleteMapping(path = "/cartao/{idCliente}/{idCartao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CartaoClienteTO excluirCartao(@PathVariable(name = "idCliente") String idCliente, 
			                             @PathVariable(name = "idCartao") String idCartao) {
		return excluirCmd.excluir(idCliente, idCartao);
	}
	
	@GetMapping(path = "/cartao/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartaoClienteTO> listarCartoes(@PathVariable(name = "idCliente") String idCliente) {
		return getCartaoCmd.getCartoesCliente(idCliente);
	}
	
	@GetMapping(path = "/cartao/{idCliente}/{idCartao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CartaoClienteTO getCartao(@PathVariable(name = "idCliente") String idCliente, 
			                         @PathVariable(name = "idCartao") String idCartao) {
		return getCartaoCmd.getCartaoCliente(idCliente, idCartao);
	}

	@GetMapping(path = "/bandeira/cartao/{numeroCartao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BandeiraCartaoTO getBandeiraCartao(@PathVariable(name = "numeroCartao") String numeroCartao) {
		return getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(numeroCartao);
	}

	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public CriarCartaoClienteTO criarCartao(@RequestBody CriarCartaoClienteTO cartaoTO) {
		return criarCartaoCmd.criarCartao(cartaoTO);
	}
	
	
}
