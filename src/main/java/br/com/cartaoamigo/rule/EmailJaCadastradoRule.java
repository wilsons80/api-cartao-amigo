package br.com.cartaoamigo.rule;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;

@Component
public class EmailJaCadastradoRule {

	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	
	public void verificar(String email, Long idPessoaFisica) {
		Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findByEmailDeOutraPessoaFisica(email, idPessoaFisica);
		if(pessoaFisica.isPresent()) {
			throw new PessoaFisicaJaExisteException("Já existe esse e-mail cadastrado.");
		}
	}

}
