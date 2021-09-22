package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.TipoEspecialidadeRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;


@Component
public class ExcluirTipoEspecialidadeCmd {
	
	@Autowired private TipoEspecialidadeRepository repository;
	
	public void excluir(Long idTipoEspecialidade) {
		if(Objects.isNull(idTipoEspecialidade)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o tipo de especialidade. Parâmetro ausente.");
		}
		repository.deleteById(idTipoEspecialidade);
	}

}
