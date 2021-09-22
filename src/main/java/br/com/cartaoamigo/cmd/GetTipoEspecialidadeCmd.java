package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoEspecialidadeTOBuilder;
import br.com.cartaoamigo.dao.GetTipoEspecialidadeDao;
import br.com.cartaoamigo.dao.dto.TipoEspecialidadeComboDTO;
import br.com.cartaoamigo.dao.repository.TipoEspecialidadeRepository;
import br.com.cartaoamigo.entity.TipoEspecialidade;
import br.com.cartaoamigo.to.TipoEspecialidadeTO;

@Component
public class GetTipoEspecialidadeCmd {
	
	@Autowired private TipoEspecialidadeRepository repository;
	@Autowired private TipoEspecialidadeTOBuilder tobuilder;
	@Autowired private GetTipoEspecialidadeDao getTipoEspecialidadeDao;
	
	
	public List<TipoEspecialidadeComboDTO> getAllCombo() {
		List<TipoEspecialidadeComboDTO> associados = getTipoEspecialidadeDao.getAllCombo();
		if(associados.isEmpty()) {return null;}
		return associados;
	}
	
	
	public  List<TipoEspecialidadeTO> buscarTodos( ) {
		Optional<List<TipoEspecialidade>> lista = repository.getListaAll();
		if(lista.isPresent()) {
			return tobuilder.buildAll(lista.get());
		}
		return null;
	}
	
	
	public TipoEspecialidadeTO buscarPorId(Long id) {
		return tobuilder.buildTO(repository.findById(id).orElse(null));
	}
}
