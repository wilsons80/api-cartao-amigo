package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.RedefinirSenhaTOBuilder;
import br.com.cartaoamigo.dao.repository.RedefinirSenhaRepository;
import br.com.cartaoamigo.entity.RedefinirSenha;
import br.com.cartaoamigo.exception.RedefinirSenhaPeriodoExpiradoException;
import br.com.cartaoamigo.to.RedefinirSenhaTO;

@Component
public class GetRedefinirSenhaCmd {
	
	@Autowired private RedefinirSenhaRepository repository;
	@Autowired private RedefinirSenhaTOBuilder toBuilder;
	
	public RedefinirSenhaTO findByCodigoValidacao(String codigo) {
		Optional<RedefinirSenha> entity = repository.findByCodigoValidacao(codigo);
		
		if(entity.isPresent()) {
			if(entity.get().getDataValidade().isBefore(LocalDateTime.now())) {
				throw new RedefinirSenhaPeriodoExpiradoException("O período para redefinir essa senha já expirou.");
			}
			return toBuilder.buildTO(entity.get());
		}

		return null;
	}
	
}
