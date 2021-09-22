package br.com.cartaoamigo.cmd;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TipoUsuarioRepository;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.TipoUsuario;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.UsuarioLogadoNotFoundException;
import br.com.cartaoamigo.security.UsuarioLocals;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@Component
public class GetUsuarioLogadoCmd {

	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private TipoUsuarioRepository tipoUsuarioRepository;

	
	public Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(Objects.isNull(authentication)) {
			throw new NotFoundException("Problema ao recuperar o usuário autenticado.");
		}
		return authentication;
	}
	
	
	public boolean temUsuarioLogado() {
		Authentication authentication = getAuthentication();
		String username = authentication.getName();
		return StringUtils.isNotEmpty(username);
	}
	
	public UsuarioLogadoTO getUsuarioLogado() {
		Authentication authentication = getAuthentication();
		String username = authentication.getName();
		
		UsuarioLogadoTO usuarioLogado = (UsuarioLogadoTO) UsuarioLocals.get(username);
		if(usuarioLogado == null) {
			throw new UsuarioLogadoNotFoundException("Erro ao recuperar o usuário logado.");
		}

		return usuarioLogado;
	}
	
	public boolean isUsuarioLogodoRoot() {
		return getTipoUsuarioLogado().getTipo().equals(TipoUsuarioSistema.ROOT.getTipo());
	}
	
	public TipoUsuarioSistema getTipoUsuarioLogado() {
		TipoUsuarioSistema tipo = null;
		Optional<TipoAcessoUsuario> tipoAcessoUsuarioAcesso = tipoAcessoUsuarioRepository.findByUsuario(getUsuarioLogado().getIdUsuario());
		if(tipoAcessoUsuarioAcesso.isPresent()) {
			Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(tipoAcessoUsuarioAcesso.get().getIdTipoUsuario());
			tipo = TipoUsuarioSistema.getPorTipo(tipoUsuario.get().getDescricao());
		}
		
		return tipo;
	}
	
}
