package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;

@Component
public class ExcluirPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;

	public void excluirPorId(Long idPessoa) {

		if (Objects.isNull(idPessoa)) {
			throw new ParametroNaoInformadoException("Identificador da pessoa não informado.");
		}
		
		PessoaFisica pessoa = repository.findById(idPessoa).orElseThrow(() -> new PessoaFisicaNaoEncontradaException("Pessoa informada não existe."));
		
		repository.delete(pessoa);
	}

	public void excluir(PessoaFisica pessoasFisica) {
		excluirPorId(pessoasFisica.getId());
		
	}
}
