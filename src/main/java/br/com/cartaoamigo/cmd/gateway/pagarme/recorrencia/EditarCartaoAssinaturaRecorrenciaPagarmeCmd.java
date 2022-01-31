package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetCarteiraCartaoPagamentoAssociadoCmd;
import br.com.cartaoamigo.cmd.GetHistoricoPagamentoCmd;
import br.com.cartaoamigo.dao.repository.CarteiraCartaoPagamentoAssociadoRepository;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.EditarCartaoAssinaturaPagarmeTO;


@Component
public class EditarCartaoAssinaturaRecorrenciaPagarmeCmd {

	@Autowired private AssinaturaPlanoRecorrenciaService service;
	@Autowired private GetCarteiraCartaoPagamentoAssociadoCmd getCarteiraCartaoPagamentoAssociadoCmd;
	@Autowired private CarteiraCartaoPagamentoAssociadoRepository cartaoPagamentoAssociadoRepository;
	@Autowired private GetHistoricoPagamentoCmd getHistoricoPagamentoCmd; 
	@Autowired private HistoricoPagamentoRepository historicoPagamentoRepository;
	
	
	public AssinaturaPlanoTO editarCartaoAssinatura(String idAssinatura, EditarCartaoAssinaturaPagarmeTO cartaoTO) {
		try {
			if(StringUtils.isEmpty(cartaoTO.getCard_id())){ 
				throw new CamposObrigatoriosException("O cartão não foi encontrado.");
			}
			
			AssinaturaPlanoTO assinaturaTO = service.editarCartaoAssinatura(idAssinatura, cartaoTO);
			
			HistoricoPagamento historicoPagamento = getHistoricoPagamentoCmd.getByAssinaturaPagarMeAndIdTitular(cartaoTO.getIdAsinaturaPagarMe(), cartaoTO.getIdTitular());
			
			if(Objects.nonNull(historicoPagamento)) {
				CarteiraCartaoPagamentoAssociado cartaoAssociado = getCarteiraCartaoPagamentoAssociadoCmd.getByIdCartaoPagarmeAndIdTitular(cartaoTO.getCard_id(), cartaoTO.getIdTitular());
				cartaoAssociado.setIdCartaoPagarMe  (assinaturaTO.getCard().getId());
				cartaoAssociado.setMesValidade      (assinaturaTO.getCard().getExp_month());
				cartaoAssociado.setAnoValidade      (String.valueOf(assinaturaTO.getCard().getExp_year()));
				cartaoAssociado.setBandeira         (assinaturaTO.getCard().getBrand());
				cartaoAssociado.setPrimeiros6digitos(assinaturaTO.getCard().getFirst_six_digits());
				cartaoAssociado.setUltimos4digitos  (assinaturaTO.getCard().getLast_four_digits());
				
				cartaoPagamentoAssociadoRepository.save(cartaoAssociado);
				
				historicoPagamento.setCartaoPagamento(cartaoAssociado);
				historicoPagamentoRepository.save(historicoPagamento);
			}
			
			return assinaturaTO;
			
		} catch (Exception e) {
			throw new PagarmeException("Erro: " + e.getMessage());
		}
	}
	
}
