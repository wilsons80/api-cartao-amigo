package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PerfilAcessoUsuarioTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;

@Component
public class GetPerfilAcessoUsuarioCmd {

	@Autowired private PerfilAcessoUsuarioRepository perfilAcessoRepository;
	@Autowired private PerfilAcessoUsuarioTOBuilder perfilAcessoUsuarioTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public List<PerfilAcessoUsuarioTO> getAllByUsuarioLogado() {
		return getAllByUsuario(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
	}

	
	public List<PerfilAcessoUsuarioTO> getAllByUsuario(Long idUsuario) {
		Optional<List<PerfilAcessoUsuario>> entitys = perfilAcessoRepository.findAllComAcessoByUsuario(idUsuario);
		if (!entitys.isPresent()) {
			return new ArrayList<PerfilAcessoUsuarioTO>();
		}
		return perfilAcessoUsuarioTOBuilder.buildAll(entitys.get());
	}
	


}
