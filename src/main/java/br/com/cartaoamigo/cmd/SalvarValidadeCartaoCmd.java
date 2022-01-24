package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.entity.Cartao;

@Component
public class SalvarValidadeCartaoCmd {
	
	@Autowired private CartaoRepository cartaoRepository;
	@Autowired private GetHistoricoPagamentoCmd getHistoricoPagamentoCmd;
	
	
	public void incrementarValidade(Long idPessoaFisicaTitular, int qtdDiasVigenciaPlano) {
		Optional<Cartao> cartaoTitular = cartaoRepository.findCartaoTitularByIdPessoaFisica(idPessoaFisicaTitular);
		if(cartaoTitular.isPresent()) {
			if(Objects.isNull(cartaoTitular.get().getDataValidadePlano())) {
				cartaoTitular.get().setDataValidadePlano(LocalDateTime.now());
			}
			
			//LocalDateTime novaData = cartaoTitular.get().getDataValidadePlano().plusDays(qtdDiasVigenciaPlano);
			LocalDateTime novaData = LocalDateTime.now().plusDays(qtdDiasVigenciaPlano);
			cartaoTitular.get().setDataValidadePlano(novaData);
			cartaoTitular.get().setAtivo(true);
			cartaoRepository.save(cartaoTitular.get());
		}
	}
	
	
	public void decrementarValidade(Long idPessoaFisicaTitular, int qtdParcelasPlano, Long idTitular) {
		Optional<Cartao> cartaoTitular = cartaoRepository.findCartaoTitularByIdPessoaFisica(idPessoaFisicaTitular);
		if(cartaoTitular.isPresent()) {
			boolean possuiPagamentoAprovado = getHistoricoPagamentoCmd.isPossuiPagamentoAprovado(idTitular);
			if(!possuiPagamentoAprovado) {
				cartaoTitular.get().setDataValidadePlano(null);
			} else {
				LocalDateTime novaData = cartaoTitular.get().getDataValidadePlano().minusMonths(qtdParcelasPlano);
				cartaoTitular.get().setDataValidadePlano(novaData);
			}
			cartaoRepository.save(cartaoTitular.get());
		}
	}
}
