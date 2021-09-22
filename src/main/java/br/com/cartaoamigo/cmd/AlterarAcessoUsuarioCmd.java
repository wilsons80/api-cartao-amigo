package br.com.cartaoamigo.cmd;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PerfilAcessoUsuarioTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;

@Component
public class AlterarAcessoUsuarioCmd {

	@Autowired private AlterarListaPerfilAcessoUsuarioCmd alterarListaPerfilAcessoUsuarioCmd;
	@Autowired private PerfilAcessoUsuarioTOBuilder perfilAcessoUsuarioTOBuilder;
	@Autowired private PerfilAcessoUsuarioRepository perfilAcessoUsuarioRepository;
	@Autowired private GetUsuarioCmd getUsuarioCmd;
	
	public List<PerfilAcessoUsuarioTO> alterar(List<PerfilAcessoUsuarioTO> listaTO) {
		Usuarios usuario = getUsuarioCmd.getById(listaTO.get(0).getIdUsuario());
		alterarListaPerfilAcessoUsuarioCmd.alterarAll(listaTO, usuario);
		
		Optional<List<PerfilAcessoUsuario>> entitys = perfilAcessoUsuarioRepository.findAllComAcessoByUsuario(listaTO.get(0).getIdUsuario());
		return perfilAcessoUsuarioTOBuilder.buildAll(entitys.orElse(null));
	}
}
