package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.CorretorTOBuilder;
import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.CorretorTO;

@Component
public class AlterarCorretorCmd {

	@Autowired private CorretorRepository repository;
	@Autowired private CorretorTOBuilder toBuilder;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	
	public CorretorTO alterar(CorretorTO to) {
		Corretor entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Corretor informado não existe: " + to.getId()));
		entity = toBuilder.build(to);
		
		PessoaFisica pessoaFisica = pessoaFisicaRepository.save(entity.getPessoaFisica());
		entity.setPessoaFisica(pessoaFisica);	
		
		return toBuilder.buildTO(repository.save(entity));
	}

}
