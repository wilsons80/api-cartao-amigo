package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.cartaoamigo.to.PessoaFisicaTO;

@Component
public class CadastrarPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private CamposObrigatoriosPessoaFisicaRule camposObrigatoriosPessoaFisicaRule;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;

	public PessoaFisica cadastrar(PessoaFisicaTO to) {
		camposObrigatoriosPessoaFisicaRule.verificar(to);
		
		Optional<PessoaFisica> pessoaFisicaOptional = repository.findByCpf(to.getCpf());
		if(pessoaFisicaOptional.isPresent()) {
			to = pessoaFisicaTOBuilder.buildNewTO(pessoaFisicaOptional.get(), to);
		} else {
			to.setDataCadastro(LocalDateTime.now());
		}
		
		PessoaFisica pessoaFisica = pessoaFisicaTOBuilder.build(to);
		return repository.save(pessoaFisica);
	}
}
