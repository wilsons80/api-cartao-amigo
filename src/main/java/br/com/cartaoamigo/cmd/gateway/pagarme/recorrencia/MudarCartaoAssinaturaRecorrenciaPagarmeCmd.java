package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetAssinaturasCmd;
import br.com.cartaoamigo.cmd.GetCarteiraCartaoPagamentoAssociadoCmd;
import br.com.cartaoamigo.cmd.GetHistoricoPagamentoCmd;
import br.com.cartaoamigo.dao.repository.AssinaturasRepository;
import br.com.cartaoamigo.dao.repository.CarteiraCartaoPagamentoAssociadoRepository;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.exception.CartaoComAssinaturaExpiradaException;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.infra.util.DataUtil;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.AssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.EditarCartaoAssinaturaPagarmeTO;


@Component
public class MudarCartaoAssinaturaRecorrenciaPagarmeCmd {

	@Autowired private AssinaturaPlanoRecorrenciaService service;
	@Autowired private GetCarteiraCartaoPagamentoAssociadoCmd getCarteiraCartaoPagamentoAssociadoCmd;
	@Autowired private CarteiraCartaoPagamentoAssociadoRepository cartaoPagamentoAssociadoRepository;
	@Autowired private GetHistoricoPagamentoCmd getHistoricoPagamentoCmd; 
	@Autowired private HistoricoPagamentoRepository historicoPagamentoRepository;
	@Autowired private GetAssinaturasCmd getAssinaturasCmd;
	@Autowired private AssinaturasRepository assinaturasRepository; 
	
	
	public AssinaturaPlanoTO mudarCartaoAssinatura(String idAssinaturaPagarme, EditarCartaoAssinaturaPagarmeTO cartaoTO) {
		try {
			if(StringUtils.isEmpty(cartaoTO.getCard_id())){ 
				throw new CamposObrigatoriosException("O cartão não foi encontrado.");
			}
			
			AssinaturaPlanoTO assinaturaPlanoPagarMeTO = service.editarCartaoAssinatura(idAssinaturaPagarme, cartaoTO);
			
			HistoricoPagamento historicoPagamento = getHistoricoPagamentoCmd.getByAssinaturaPagarMeAndIdTitular(idAssinaturaPagarme, cartaoTO.getIdTitular());
			
			if(Objects.nonNull(historicoPagamento)) {
				CarteiraCartaoPagamentoAssociado cartaoAssociado = getCarteiraCartaoPagamentoAssociadoCmd.getByIdCartaoPagarmeAndIdTitular(cartaoTO.getCard_id(), cartaoTO.getIdTitular());
				
				int mes = Integer.valueOf(cartaoAssociado.getMesValidade());
				int ano = Integer.valueOf(cartaoAssociado.getAnoValidade());
				if(DataUtil.isDataExpirada(mes, ano)) {
					throw new CartaoComAssinaturaExpiradaException("Não pode mudar para um cartao com data expirada.");
				}
				
				cartaoAssociado.setIdCartaoPagarMe  (assinaturaPlanoPagarMeTO.getCard().getId());
				cartaoAssociado.setMesValidade      (assinaturaPlanoPagarMeTO.getCard().getExp_month());
				cartaoAssociado.setAnoValidade      (String.valueOf(assinaturaPlanoPagarMeTO.getCard().getExp_year()));
				cartaoAssociado.setBandeira         (assinaturaPlanoPagarMeTO.getCard().getBrand());
				cartaoAssociado.setPrimeiros6digitos(assinaturaPlanoPagarMeTO.getCard().getFirst_six_digits());
				cartaoAssociado.setUltimos4digitos  (assinaturaPlanoPagarMeTO.getCard().getLast_four_digits());
				
				cartaoPagamentoAssociadoRepository.save(cartaoAssociado);
				
				historicoPagamento.setCartaoPagamento(cartaoAssociado);
				historicoPagamentoRepository.save(historicoPagamento);
				
				Assinaturas assinatura = getAssinaturasCmd.getAssinaturaCodigoPagarMe(idAssinaturaPagarme);
				if(Objects.nonNull(assinatura)) {
					assinatura.setIdCartaoPagarMe(cartaoTO.getCard_id());
					assinaturasRepository.save(assinatura);
				}
				
			}
			
			return assinaturaPlanoPagarMeTO;
			
		} catch (Exception e) {
			throw new PagarmeException("Erro: " + e.getMessage());
		}
	}
	
}
