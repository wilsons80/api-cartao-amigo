package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.entity.Cartao;

@Component
public class CancelarCartoesCmd {
	
	@Autowired private CartaoRepository cartaoRepository;
	
	public void cancelarCartoes(Long idTitular) {
		Optional<List<Cartao>> cartoes = cartaoRepository.findAllAtivoByTitular(idTitular);
		if(cartoes.isPresent()) {
			cartoes.get().stream().forEach(cartao -> {
				cartao.setAtivo(false);
				cartao.setDataValidadePlano(null);
			});
			cartaoRepository.saveAll(cartoes.get());
		}
	}
	
}
