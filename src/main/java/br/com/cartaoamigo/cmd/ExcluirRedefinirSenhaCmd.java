package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.RedefinirSenhaRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirRedefinirSenhaCmd {
	
	@Autowired private RedefinirSenhaRepository repository;
	
	public void excluirRedefinirSenha(Long idRedefinirSenha) {
		if(Objects.isNull(idRedefinirSenha)) {
			throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");
		}
		
		repository.deleteById(idRedefinirSenha);
	}

}
