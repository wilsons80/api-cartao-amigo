package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.cartaoamigo.to.PessoaFisicaTO;

@Component
public class AlterarPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private CamposObrigatoriosPessoaFisicaRule camposObrigatoriosPessoaFisicaRule;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;

	public PessoaFisica alterar(PessoaFisicaTO to) {
		camposObrigatoriosPessoaFisicaRule.verificar(to);
		PessoaFisica pessoaFisica = pessoaFisicaTOBuilder.build(to);
		return repository.save(pessoaFisica);
	}
}
