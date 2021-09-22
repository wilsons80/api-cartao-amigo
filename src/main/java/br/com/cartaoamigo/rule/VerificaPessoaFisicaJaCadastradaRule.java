package br.com.cartaoamigo.rule;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;
import br.com.cartaoamigo.infra.util.StringUtil;

@Component
public class VerificaPessoaFisicaJaCadastradaRule {
	
	@Autowired private PessoaFisicaRepository repository;
	
	public void verificar(String cpf) {
		if(StringUtils.isNotEmpty(cpf)) {
			Optional<PessoaFisica> pessoa = repository.findByCpf(cpf);
			if(pessoa.isPresent()) {
				throw new PessoaFisicaJaExisteException("Já existe uma pessoa cadastrada com esse CPF: " + StringUtil.mascaraCPF(cpf));
			}
		}
	}
}
