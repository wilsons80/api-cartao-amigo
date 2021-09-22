package br.com.cartaoamigo.ws.pagseguro.split.condicoesparcelamento;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.CondicaoParcelamentoTO;
import br.com.cartaoamigo.ws.pagseguro.to.CondicoesParcelamentoBandeirasTO;
import br.com.cartaoamigo.ws.pagseguro.to.ParcelasBandeiraTO;


@Component
public class CondicoesParcelamentoSplitServiceImpl implements CondicoesParcelamentoSplitService {

	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	@Override
	public CondicaoParcelamentoTO getCondicoesParcelamento(String idSessao, 
			                                               Double valor, 
			                                               String bandeiraCartao, 
			                                               Long maxParcelasSemJuros) throws Exception {
		HashMap<String, String> parametrosHeader = new HashMap<>();
		parametrosHeader.put("Content-type", "application/json;charset=UTF-8");
		
		CondicaoParcelamentoTO parcelas = new CondicaoParcelamentoTO();
		
		CondicoesParcelamentoBandeirasTO condicoesParcelas = httpRestUtil.get(pagSeguroProvider.getUrlOpcoesParcelamento(idSessao, valor, bandeiraCartao, maxParcelasSemJuros), CondicoesParcelamentoBandeirasTO.class, parametrosHeader);
		parcelas.setError(condicoesParcelas.getError());
		parcelas.setParcelas(getParcelas(condicoesParcelas));
		
		return parcelas;
	}
	
	private List<ParcelasBandeiraTO> getParcelas(CondicoesParcelamentoBandeirasTO condicoes) {
		if(Objects.nonNull(condicoes.getInstallments().getVisa()) && !condicoes.getInstallments().getVisa().isEmpty()) {
			return condicoes.getInstallments().getVisa();
		}
		if(Objects.nonNull(condicoes.getInstallments().getValecard()) && !condicoes.getInstallments().getValecard().isEmpty()) {
			return condicoes.getInstallments().getValecard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getUpbrasil()) && !condicoes.getInstallments().getUpbrasil().isEmpty()) {
			return condicoes.getInstallments().getUpbrasil();
		}
		if(Objects.nonNull(condicoes.getInstallments().getSorocred()) && !condicoes.getInstallments().getSorocred().isEmpty()) {
			return condicoes.getInstallments().getSorocred();
		}
		if(Objects.nonNull(condicoes.getInstallments().getPersonalcard()) && !condicoes.getInstallments().getPersonalcard().isEmpty()) {
			return condicoes.getInstallments().getPersonalcard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getMastercard()) && !condicoes.getInstallments().getMastercard().isEmpty()) {
			return condicoes.getInstallments().getMastercard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getMais()) && !condicoes.getInstallments().getMais().isEmpty()) {
			return condicoes.getInstallments().getMais();
		}
		if(Objects.nonNull(condicoes.getInstallments().getHipercard()) && !condicoes.getInstallments().getHipercard().isEmpty()) {
			return condicoes.getInstallments().getHipercard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getGrandcard()) && !condicoes.getInstallments().getGrandcard().isEmpty()) {
			return condicoes.getInstallments().getGrandcard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getFortbrasil()) && !condicoes.getInstallments().getFortbrasil().isEmpty()) {
			return condicoes.getInstallments().getFortbrasil();
		}
		if(Objects.nonNull(condicoes.getInstallments().getElo()) && !condicoes.getInstallments().getElo().isEmpty()) {
			return condicoes.getInstallments().getElo();
		}
		if(Objects.nonNull(condicoes.getInstallments().getDiners()) && !condicoes.getInstallments().getDiners().isEmpty()) {
			return condicoes.getInstallments().getDiners();
		}
		if(Objects.nonNull(condicoes.getInstallments().getCabal()) && !condicoes.getInstallments().getCabal().isEmpty()) {
			return condicoes.getInstallments().getCabal();
		}
		if(Objects.nonNull(condicoes.getInstallments().getBrasilcard()) && !condicoes.getInstallments().getBrasilcard().isEmpty()) {
			return condicoes.getInstallments().getBrasilcard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getBanesecard()) && !condicoes.getInstallments().getBanesecard().isEmpty()) {
			return condicoes.getInstallments().getBanesecard();
		}
		if(Objects.nonNull(condicoes.getInstallments().getAura()) && !condicoes.getInstallments().getAura().isEmpty()) {
			return condicoes.getInstallments().getAura();
		}
		if(Objects.nonNull(condicoes.getInstallments().getAmex()) && !condicoes.getInstallments().getAmex().isEmpty()) {
			return condicoes.getInstallments().getAmex();
		}
		
		return null;
	}

}
