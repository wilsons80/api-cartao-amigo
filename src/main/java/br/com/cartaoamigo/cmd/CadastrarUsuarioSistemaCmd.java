package br.com.cartaoamigo.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.UsuarioJaExisteException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class CadastrarUsuarioSistemaCmd {
    
	@Autowired private CadastrarUsuarioCmd cadastrarUsuarioCmd;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private CadastrarPerfilAcessoUsuarioCmd cadastrarPerfilAcessoUsuarioCmd;
	@Autowired private GetUsuarioCmd getUsuarioCmd;
	@Autowired private UsuarioRepository usuarioRepository;
	
	public UsuariosTO cadastrar(UsuariosTO usuarioTO) {
		if(NumeroUtil.isNumero(usuarioTO.getUsername())) {
			throw new UsuarioJaExisteException("O login do usuário deve ter pelo menos uma letra.");
		}
		
		Optional<Usuarios> loginJaExiste = usuarioRepository.findByUsername(usuarioTO.getUsername());
		if(loginJaExiste.isPresent()) {
			throw new UsuarioJaExisteException("Já existe um usuário com o login: " + usuarioTO.getUsername());
		}
		
		UsuariosTO usuarioSalvo = cadastrarUsuarioCmd.cadastrar(usuarioTO);
		
		TipoAcessoUsuario tau = new TipoAcessoUsuario();
		tau.setId(null);
		
		TipoUsuarioSistema tipoUsuarioTO = TipoUsuarioSistema.getPorId(usuarioTO.getTipoUsuario().getId());
		tau.setIdTipoUsuario(tipoUsuarioTO.getId());
		
		tau.setIdUsuario(usuarioSalvo.getId());
		tipoAcessoUsuarioRepository.save(tau);
		
		final Long idUsuario = usuarioSalvo.getId();
		if(Objects.nonNull(usuarioTO.getGruposAcesso()) && !usuarioTO.getGruposAcesso().isEmpty()) {
			usuarioTO.getGruposAcesso().forEach(p -> p.setIdUsuario(idUsuario));		
			cadastrarPerfilAcessoUsuarioCmd.cadastrarAll(usuarioTO.getGruposAcesso());
		}
		
		return getUsuarioCmd.getTOById(idUsuario);
	}

}
