package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ArquivoTOBuilder;
import br.com.cartaoamigo.dao.repository.ArquivoRepository;
import br.com.cartaoamigo.dao.repository.ArquivosMetadadosRepository;
import br.com.cartaoamigo.entity.Arquivo;
import br.com.cartaoamigo.entity.ArquivosMetadados;
import br.com.cartaoamigo.to.ArquivoTO;

@Component
public class SalvarArquivoCmd {

	@Autowired private ArquivoRepository repository;
	@Autowired private ArquivoTOBuilder toBuilder;
	@Autowired private ArquivosMetadadosRepository arquivosMetadadosRepository;
	
	
	public Arquivo salvar(ArquivoTO to) {
		Arquivo entity = toBuilder.build(to);
		
		ArquivosMetadados arquivosMetadados = arquivosMetadadosRepository.save(entity.getMetadados());
		entity.setMetadados(arquivosMetadados);
		
		return repository.save(entity);
	}

}
