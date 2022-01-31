package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.SalvarCarteiraCartaoPagamentoAssociadoCmd;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.rule.ValidarDadosCartaoClientePagarmeRule;
import br.com.cartaoamigo.ws.pagarme.cartao.CartaoClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.BandeiraCartaoTO;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;


@Component
public class EditarCartaoClienteRecorrenciaPagarmeCmd {

	@Autowired private CartaoClienteRecorrenciaService service;
	@Autowired private ValidarDadosCartaoClientePagarmeRule validarDadosCartaoClientePagarmeRule;
	@Autowired private GetBandeiraCartaoRecorrenciaPagarmeCmd getBandeiraCartaoRecorrenciaPagarmeCmd;
	@Autowired private SalvarCarteiraCartaoPagamentoAssociadoCmd salvarCarteiraCartaoPagamentoAssociadoCmd;
	
	
	public CriarCartaoClienteTO editarCartao(CriarCartaoClienteTO cartaoTO) {
		try {
			validarDadosCartaoClientePagarmeRule.validarEdicao(cartaoTO);
			
			String documento    = NumeroUtil.somenteNumeros(cartaoTO.getHolder_document());
			cartaoTO.setHolder_document(documento);
			
			String nomeImpresso = StringUtil.removerCaractereEspecial(cartaoTO.getHolder_name());
			cartaoTO.setHolder_name(nomeImpresso);
			
			// buscar a bandeira do BIN do cartão
			BandeiraCartaoTO bandeiraCartaoTO = getBandeiraCartaoRecorrenciaPagarmeCmd.getBandeira(cartaoTO.getFirst_six_digits());
			cartaoTO.setBrand(bandeiraCartaoTO.getBrand());
			
			CriarCartaoClienteTO editarCartao = service.editarCartao(cartaoTO, cartaoTO.getCustomer().getId(), cartaoTO.getId());
			
			salvarCarteiraCartaoPagamentoAssociadoCmd.salvar(editarCartao);
			
			return editarCartao;
		} catch (Exception e) {
			throw new PagarmeException("Erro: " + e.getMessage());
		}
	}
	
}
