package br.com.cartaoamigo.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ArquivoTOBuilder;
import br.com.cartaoamigo.dao.repository.ArquivoRepository;
import br.com.cartaoamigo.entity.Arquivo;
import br.com.cartaoamigo.to.ArquivoTO;

@Component
public class GetArquivosCmd {

	@Autowired private ArquivoRepository repository;
	@Autowired private ArquivoTOBuilder toBuilder;
	

	public ArquivoTO getTOById(Long id) {
		Arquivo arquivo = getById(id).orElse(null);
		return toBuilder.buildTO(arquivo);
	}

	public Optional<Arquivo> getById(Long id) {
		Optional<Arquivo> arquivo = repository.findById(id);
		return arquivo;
	}
	
	public Optional<Arquivo> getByIdMetadados(Long id) {
		Optional<Arquivo> arquivo = repository.findByIdMetadados(id);
		return arquivo;
	}
}
