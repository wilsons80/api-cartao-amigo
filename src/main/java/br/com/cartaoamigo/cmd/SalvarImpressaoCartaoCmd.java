package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.to.relatorios.ImpressaoCartaoTO;

@Component
public class SalvarImpressaoCartaoCmd {
	
	@Autowired private CartaoRepository repository;

	public void salvar(List<ImpressaoCartaoTO> dados) {		
		if(dados != null && !dados.isEmpty()) {
			dados.forEach(cartao -> {
				Optional<Cartao> cartaoOptional = repository.findById(cartao.getIdCartao());
				if(cartaoOptional.isPresent()) {
					cartaoOptional.get().setDataImpressao(LocalDateTime.now());
					repository.save(cartaoOptional.get());
				}				
			});
		}		
	}
}
