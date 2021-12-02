package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.UsuarioSemAcessoException;
import br.com.cartaoamigo.infra.util.CPF_CNPJ_Util;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.security.UsuarioLocals;
import br.com.cartaoamigo.to.LoginTO;
import br.com.cartaoamigo.to.TrocaSenhaTO;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@Component
public class AutenticadorCmd {
	
	@Autowired private AuthenticationManager authManager;
	@Autowired private TrocarSenhaCmd trocarSenhaCmd;
	@Autowired private SaveUsuarioLogadoCmd saveUsuarioLogadoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;    
	@Autowired private TokenJwtCmd tokenJwtCmd;
	
	public UsuarioLogadoTO autenticar(LoginTO user) {
		UsuarioLogadoTO usuarioLogadoTO;
		try {
			UsuarioLocals.removerSessoesInvalidas();
			
			// Valida se o acesso é por CPF
			String loginCPF = NumeroUtil.somenteNumeros(user.getLogin().toLowerCase());
			if(NumeroUtil.isNumero(loginCPF) && loginCPF.length() == 11 && CPF_CNPJ_Util.isCPF(loginCPF)) {	
				user.setLogin(loginCPF);
			}
			
			UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user.getLogin().toLowerCase() ,user.getSenha());
			Authentication auth = authManager.authenticate(userAuth);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			usuarioLogadoTO = saveUsuarioLogadoCmd.save(auth);
			UsuarioLocals.set(auth.getName(), usuarioLogadoTO);
			
		} catch (BadCredentialsException e) {
			throw new UsuarioSemAcessoException("Usuário/Senha inválidos.");
		}
		
		return usuarioLogadoTO;
	}


	public UsuarioLogadoTO refreshToken() {
		Authentication auth = getUsuarioLogadoCmd.getAuthentication();
		String tokenJwt = tokenJwtCmd.createToken(auth.getName(), auth.getAuthorities());
		
		UsuarioLogadoTO usuarioLogadoTO = saveUsuarioLogadoCmd.save(auth);
		usuarioLogadoTO.setToken(tokenJwt);

		UsuarioLocals.set(auth.getName(), usuarioLogadoTO);
		return usuarioLogadoTO;
	}
	
	
	public void trocarSenha(TrocaSenhaTO trocaSenhaTO) {
		trocarSenhaCmd.trocarSenha(trocaSenhaTO);
	}

}
