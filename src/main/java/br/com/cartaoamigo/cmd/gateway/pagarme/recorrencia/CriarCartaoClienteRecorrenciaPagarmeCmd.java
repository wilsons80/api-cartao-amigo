package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.rule.ValidarDadosCartaoClientePagarmeRule;
import br.com.cartaoamigo.ws.pagarme.cartao.CartaoClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;


@Component
public class CriarCartaoClienteRecorrenciaPagarmeCmd {

	@Autowired private CartaoClienteRecorrenciaService service;
	@Autowired private ValidarDadosCartaoClientePagarmeRule validarDadosCartaoClientePagarmeRule;
	@Autowired private GetBandeiraCartaoRecorrenciaPagarmeCmd getBandeiraCartaoRecorrenciaPagarmeCmd;
	
	public CriarCartaoClienteTO criarCartao(CriarCartaoClienteTO cartaoTO) {
		try {
			validarDadosCartaoClientePagarmeRule.validar(cartaoTO);
			
			String numeroCartao = NumeroUtil.somenteNumeros(cartaoTO.getNumber());
			cartaoTO.setNumber(numeroCartao);
			
			String documento    = NumeroUtil.somenteNumeros(cartaoTO.getHolder_document());
			cartaoTO.setHolder_document(documento);
			
			String nomeImpresso = StringUtil.removerCaractereEspecial(cartaoTO.getHolder_name());
			cartaoTO.setHolder_name(nomeImpresso);
			
			// buscar a bandeira do BIN do cartão
			BandeiraCartaoTO bandeiraCartaoTO = getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(cartaoTO.getNumber());
			cartaoTO.setBrand(bandeiraCartaoTO.getBrand());
			
			return service.criarCarto(cartaoTO, cartaoTO.getCustomer().getId());
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao criar o cartão: " + e.getMessage());
		}
	}

}
