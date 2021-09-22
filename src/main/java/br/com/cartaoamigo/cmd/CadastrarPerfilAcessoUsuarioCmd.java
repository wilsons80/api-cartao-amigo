package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoTOBuilder;
import br.com.cartaoamigo.builder.PerfilAcessoUsuarioTOBuilder;
import br.com.cartaoamigo.builder.UsuariosTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class CadastrarPerfilAcessoUsuarioCmd {

	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private PerfilAcessoUsuarioRepository perfilAcessoUsuarioRepository;
	@Autowired private GrupoAcessoTOBuilder grupoAcessoTOBuilder;
	@Autowired private PerfilAcessoUsuarioTOBuilder perfilAcessoUsuarioTOBuilder;
	@Autowired private UsuariosTOBuilder usuariosTOBuilder;
	
	public List<PerfilAcessoUsuarioTO> cadastrarAll(List<PerfilAcessoUsuarioTO> listaTO) {
		Long idUsuario = listaTO.get(0).getIdUsuario();
		Optional<Usuarios> usuario = usuarioRepository.findById(idUsuario);
		if(!usuario.isPresent()) {
			throw new NotFoundException("Usuario informado não existe.");
		}			
		
		Optional.ofNullable(listaTO).ifPresent(lista -> {
			lista.forEach(acessoTO -> cadastrar(acessoTO, usuariosTOBuilder.buildTO(usuario.get())));
		});
		
		Optional<List<PerfilAcessoUsuario>> entitys = perfilAcessoUsuarioRepository.findAllComAcessoByUsuario(idUsuario);
		return perfilAcessoUsuarioTOBuilder.buildAll(entitys.orElse(null));
		
	}
	
	public void cadastrar(PerfilAcessoUsuarioTO to, UsuariosTO usuarioTO) {
		PerfilAcessoUsuario entity = new PerfilAcessoUsuario();
		entity.setId(to.getId());
		entity.setGrupoAcesso(grupoAcessoTOBuilder.build(to.getGrupoAcesso()));
		entity.setIdUsuario(usuarioTO.getId());
		
		perfilAcessoUsuarioRepository.save(entity);
	}
	
}
