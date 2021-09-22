package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.MedicoEspecialidadesRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirMedicoEspecialidadesCmd {
	
	@Autowired private MedicoEspecialidadesRepository repository;
	
	public void excluir(Long idMedicoEspecialidades) {
		if(Objects.isNull(idMedicoEspecialidades)) {
			throw new ParametroNaoInformadoException("Erro ao excluir especialidade médico. Parâmetro ausente.");
		}
		repository.deleteById(idMedicoEspecialidades);
	}

}
