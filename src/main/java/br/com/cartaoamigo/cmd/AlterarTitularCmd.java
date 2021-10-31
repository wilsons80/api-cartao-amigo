package br.com.cartaoamigo.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.builder.TitularTOBuilder;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.rule.CampoObrigatorioTitularRule;
import br.com.cartaoamigo.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.cartaoamigo.rule.DependenteLimitadorRule;
import br.com.cartaoamigo.to.TitularTO;

@Component
public class AlterarTitularCmd {

	@Autowired private CampoObrigatorioTitularRule rule;
	@Autowired private DependenteLimitadorRule dependenteLimitadorRule;
	@Autowired private TitularRepository repository;
	@Autowired private TitularTOBuilder toBuilder;
	@Autowired private AlterarListaDependentesCmd alterarListaDependentesCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private CamposObrigatoriosPessoaFisicaRule camposObrigatoriosPessoaFisicaRule;

	public TitularTO alterar(TitularTO titularTO) {
		rule.verificar(titularTO);
		dependenteLimitadorRule.validar(titularTO.getDependentes());
		camposObrigatoriosPessoaFisicaRule.verificar(titularTO.getPessoaFisica());
		
		Optional<Titular> email = repository.findByEmailOutroCpf(titularTO.getPessoaFisica().getEmail().toUpperCase(), NumeroUtil.somenteNumeros(titularTO.getPessoaFisica().getCpf()));
		if(email.isPresent()) {
			throw new PessoaFisicaJaExisteException("Esse e-mail já está cadastrado para outro CPF em nossa base de dados.");
		}
		
		Titular entity = repository.findById(titularTO.getId()).orElseThrow(() -> new NotFoundException("Titular informado não existe."));
		
		PessoaFisica pessoaFisica = pessoaFisicaTOBuilder.build(titularTO.getPessoaFisica());
		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
		entity.setPessoaFisica(pessoaFisica);
		
		entity = repository.save(toBuilder.build(titularTO));
		
		alterarListaDependentesCmd.alterarAll(titularTO.getDependentes(), entity);
		
		return toBuilder.buildTO(repository.findById(entity.getId()).get());
	}

}
