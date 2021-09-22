package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.ArquivoRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirArquivoCmd {

	@Autowired
	private ArquivoRepository repository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
		}
		repository.deleteById(id);
	}
}
