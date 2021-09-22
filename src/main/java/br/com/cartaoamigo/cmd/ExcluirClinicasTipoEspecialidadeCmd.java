package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.ClinicasTipoEspecialidadesRepository;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.exception.ClinicaNaoEncontradaException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirClinicasTipoEspecialidadeCmd {

	@Autowired private ClinicasTipoEspecialidadesRepository repository;
	
	
	public void excluir(Long idClinicaTipoEspecialidade) {
		if (Objects.isNull(idClinicaTipoEspecialidade)) {
			throw new ParametroNaoInformadoException("Identificador não informado.");
		}
		
		ClinicasTipoEspecialidades entity = repository.findById(idClinicaTipoEspecialidade).orElseThrow(() -> new ClinicaNaoEncontradaException("Tipo de especialiade da clínica informado não existe: " + idClinicaTipoEspecialidade));
		repository.delete(entity);
	}

}
