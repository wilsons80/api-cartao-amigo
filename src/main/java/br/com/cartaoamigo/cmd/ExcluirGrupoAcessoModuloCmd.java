package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.GrupoAcessoModulosRepository;
import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirGrupoAcessoModuloCmd {

	@Autowired private GrupoAcessoModulosRepository repository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
		}
		repository.deleteById(id);
	}
	
	
	public void excluir(GrupoAcessoModulos entity) {
		Long idGrupoAcesso = entity.getIdGrupoAcesso();
		Long idModuloPai   = entity.getModulo().getModuloPai().getId();
		Long idModuloFilho = entity.getModulo().getId();
		
		repository.deleteById(entity.getId());
		
		Optional<List<GrupoAcessoModulos>> outrosModulosFilhos = repository.findOutrosModulosFilhos(idGrupoAcesso, idModuloPai, idModuloFilho);
		if(outrosModulosFilhos.isPresent()) {
			Optional<List<GrupoAcessoModulos>> moduloPai = repository.findModuloPai(idGrupoAcesso, idModuloPai);
			repository.deleteAll(moduloPai.get());
		}
	}
	
}
