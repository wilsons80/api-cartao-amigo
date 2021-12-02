package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.ModuloDao;
import br.com.cartaoamigo.dao.dto.ModuloAcessoDTO;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.CpfInvalidoException;
import br.com.cartaoamigo.exception.UsuarioSemAcessoException;
import br.com.cartaoamigo.infra.util.CPF_CNPJ_Util;
import br.com.cartaoamigo.infra.util.NumeroUtil;


@Component
public class GetUsuarioAutenticadoCmd implements UserDetailsService{
	
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private ModuloDao moduloDao;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User userSpring = null;
		Optional<Usuarios> usuario = null;
		
		String loginCPF = NumeroUtil.somenteNumeros(login);
		
		// Valida se o acesso é por CPF
		if(NumeroUtil.isNumero(loginCPF) && loginCPF.length() == 11) {			
			String username = loginCPF;
			if(!CPF_CNPJ_Util.isCPF(username)) {throw new CpfInvalidoException("O login informado está inválido.");}			
			usuario = usuarioRepository.findByUsernameUsuario(username);
		} else {
			usuario = usuarioRepository.findByUsername(login);
		}

		if(!usuario.isPresent()) throw new UsernameNotFoundException("Usuário não existe.");
		
		Usuarios user = usuario.get();
		if (user.getStAtivo() == Boolean.FALSE) {
			throw new UsuarioSemAcessoException("Usuário inativo.");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByUsuario(usuario.get().getId());
		if(tipoAcessoUsuario.isPresent()) {
			List<ModuloAcessoDTO> modulosComAcesso = moduloDao.getAllModuloComAcesso(user.getId(), tipoAcessoUsuario.get().getId());
			modulosComAcesso.stream().forEach(m -> roles.add(new SimpleGrantedAuthority("ROLE_" + m.getNome().toUpperCase())));
		}
		
		List<GrantedAuthority> authorities = roles;
		userSpring = new User(login, user.getSenha(), authorities);
	
		return userSpring;
	}
	

	public Usuarios loadUserById(Long id) throws UsernameNotFoundException {
		Optional<Usuarios> result = usuarioRepository.findById(id);
		if(!result.isPresent()) throw new UsernameNotFoundException("Não existe usuário com o id = " + id);
		Usuarios user = result.get();
		return user;
	}

}
