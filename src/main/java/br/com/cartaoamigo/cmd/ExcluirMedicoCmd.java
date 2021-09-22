package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.MedicosRepository;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirMedicoCmd {
	
	@Autowired private MedicosRepository repository;
	
	public void excluir(Long idMedico) {
		if(Objects.isNull(idMedico)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o médico. Parâmetro ausente.");
		}
		
		repository.deleteById(idMedico);
	}

}
