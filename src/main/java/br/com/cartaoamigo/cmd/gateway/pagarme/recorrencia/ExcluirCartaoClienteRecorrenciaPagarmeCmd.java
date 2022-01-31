package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetAssinaturasCmd;
import br.com.cartaoamigo.cmd.GetCarteiraCartaoPagamentoAssociadoCmd;
import br.com.cartaoamigo.dao.repository.CarteiraCartaoPagamentoAssociadoRepository;
import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.exception.CartaoComAssinaturaExpiradaException;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.cartao.CartaoClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.CartaoClienteTO;


@Component
public class ExcluirCartaoClienteRecorrenciaPagarmeCmd {

	@Autowired private CartaoClienteRecorrenciaService service;
	@Autowired private GetCarteiraCartaoPagamentoAssociadoCmd getCarteiraCartaoPagamentoAssociadoCmd;
	@Autowired private CarteiraCartaoPagamentoAssociadoRepository carteiraCartaoPagamentoAssociadoRepository;
	@Autowired private GetAssinaturasCmd getAssinaturasCmd;
	
	public CartaoClienteTO excluir(Long idTitular, String idClientePagarMe, String idCartaoPagarMe) {
		try {
			Optional<Assinaturas> assinaturaVigente = getAssinaturasCmd.findAssinaturaAtivaByTitularAndIdCartaoPagarMe(idTitular, idCartaoPagarMe);
			if(assinaturaVigente.isPresent()) {
				throw new CartaoComAssinaturaExpiradaException("Esse cartão está vinculado à assinatura vigente, mude o cartão na assinatura para poder excluir esse cartão.");
			}
			
			CarteiraCartaoPagamentoAssociado cartaoAssociado = getCarteiraCartaoPagamentoAssociadoCmd.getByIdCartaoPagarme(idCartaoPagarMe);
			if(Objects.nonNull(cartaoAssociado)) {
				cartaoAssociado.setExclusaoLogica(true);
				carteiraCartaoPagamentoAssociadoRepository.save(cartaoAssociado);
			}
			
			return service.excluirCartao(idClientePagarMe, idCartaoPagarMe);
			
		} catch (Exception e) {
			throw new PagarmeException("Erro ao excluir o cartão: " + e.getMessage());
		}
	}

}
