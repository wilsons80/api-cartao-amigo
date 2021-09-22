package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.GrupoAcessoModulosRepository;
import br.com.cartaoamigo.dao.repository.GrupoAcessoRepository;
import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.exception.GrupoAcessoJaAtribuidoParaUsuarioException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirGrupoAcessoCmd {

	@Autowired private GrupoAcessoRepository repository;
	@Autowired private GrupoAcessoModulosRepository grupoAcessoModulosRepository;
	@Autowired private PerfilAcessoUsuarioRepository perfilAcessoUsuarioRepository;
	
	public void excluir(Long idGrupoAcesso) {
		if(Objects.isNull(idGrupoAcesso)) {
			throw new ParametroNaoInformadoException("Erro ao excluir. Parâmetro ausente.");
		}
		
		Optional<List<PerfilAcessoUsuario>> perfilAcessoUsuario = perfilAcessoUsuarioRepository.findAllByGrupoAcesso(idGrupoAcesso);
		if(perfilAcessoUsuario.isPresent()) {
			throw new GrupoAcessoJaAtribuidoParaUsuarioException("Não é possível excluir esse grupo de acesso, pois já está atribuído para usuários.");
		}
		
		Optional<List<GrupoAcessoModulos>> gruposAcessoModulos = grupoAcessoModulosRepository.findAllByGrupoAcesso(idGrupoAcesso);
		if(gruposAcessoModulos.isPresent()) {
			grupoAcessoModulosRepository.deleteAll(gruposAcessoModulos.get());
		}
		
		repository.deleteById(idGrupoAcesso);
	}
}
