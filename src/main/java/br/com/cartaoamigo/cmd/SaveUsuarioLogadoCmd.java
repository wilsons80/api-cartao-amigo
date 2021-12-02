package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ModuloTOBuilder;
import br.com.cartaoamigo.builder.TipoUsuarioTOBuilder;
import br.com.cartaoamigo.dao.repository.ModuloRepository;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TipoUsuarioRepository;
import br.com.cartaoamigo.entity.Modulo;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.TipoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.SemAcessoUnidadeException;
import br.com.cartaoamigo.security.UsuarioLocals;
import br.com.cartaoamigo.to.AcessoModuloTO;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@Component
public class SaveUsuarioLogadoCmd {
	
	@Autowired private TokenJwtCmd createTokenJwtCmd;
	@Autowired private GetUsuarioCmd getUsuarioCmd;
	@Autowired private ModuloRepository moduloRepository;
	@Autowired private ModuloTOBuilder moduloTOBuilder;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired private TipoUsuarioTOBuilder tipoUsuarioTOBuilder;
	
	
	public void reset(String username) {
		UsuarioLocals.set(username, null);
	}
	
	@Transactional
	public UsuarioLogadoTO save(Authentication auth) {
		String username = auth.getName();
		return montarUsuarioLogado(username, auth.getAuthorities());
	}
	
	
	private UsuarioLogadoTO montarUsuarioLogado(String username, Collection<? extends GrantedAuthority> authorities) {
		String jwt = createTokenJwtCmd.createToken(username, authorities);
		
		UsuarioLogadoTO usuarioLogadoTO = UsuarioLocals.get(username);
		if(usuarioLogadoTO == null) {
			usuarioLogadoTO = new UsuarioLogadoTO();
		}
		
		Usuarios usuarios = getUsuarioCmd.get(username);
		usuarioLogadoTO.setIdUsuario(usuarios.getId());
		usuarioLogadoTO.setToken(jwt);
		usuarioLogadoTO.setNomeUsuario(usuarios.getPessoaFisica().getNome());
		usuarioLogadoTO.setUsername(username);
		usuarioLogadoTO.setTrocarSenha(usuarios.getStTrocaSenha());
		usuarioLogadoTO.setIdPessoaFisica(usuarios.getPessoaFisica().getId());
		usuarioLogadoTO.setEmail(usuarios.getPessoaFisica().getEmail());
		
		Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByUsuario(usuarios.getId());
		if(tipoAcessoUsuario.isPresent()) {
			Long idTipoUsuario = tipoAcessoUsuario.get().getIdTipoUsuario();
			
			Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(idTipoUsuario);
			if(tipoUsuario.isPresent()) {
				usuarioLogadoTO.setTipoUsuario(tipoUsuarioTOBuilder.buildTO(tipoUsuario.get()));
			}
		}
		
		List<AcessoModuloTO> modulos = new ArrayList<>();
		authorities.stream().forEach( autho -> {
			String nomeModulo = autho.getAuthority().replace("ROLE_", "");
			Optional<Modulo> modulo = moduloRepository.findPorNome(nomeModulo);
			
			if(modulo.isPresent()) {
				modulos.add(moduloTOBuilder.buildAcessoModuloTO(modulo.get()));
			}
		});
		usuarioLogadoTO.setModulos(modulos);
		
		if( usuarioLogadoTO.getModulos().isEmpty() ) {
			throw new SemAcessoUnidadeException("Usuário não possui acesso em Módulos.");
		}
				
		return usuarioLogadoTO;
	}	
	
}
