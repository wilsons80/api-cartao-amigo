package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.AcessoDao;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.rule.ValidarTrocaSenhaRule;
import br.com.cartaoamigo.security.CustomPasswordEncoder;
import br.com.cartaoamigo.to.TrocaSenhaTO;

@Component
public class TrocarSenhaCmd {
	
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	@Autowired private AcessoDao acessoDao;
	@Autowired private ValidarTrocaSenhaRule validarTrocaSenhaRule;
	@Autowired private GetUsuarioCmd getUsuarioCmd;
	
	public void trocarSenha(TrocaSenhaTO trocaSenhaTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(Objects.isNull(authentication)) {
			throw new NotFoundException("Problema ao recuperar o usuário logado.");
		}
		
		String usernameSemIDSESSION = authentication.getName().substring(0, authentication.getName().indexOf("@@"));
		Usuarios usuario = getUsuarioCmd.get(usernameSemIDSESSION);
		
		validarTrocaSenhaRule.validar(usuario.getSenha(), trocaSenhaTO.getSenhaAtual(), trocaSenhaTO.getSenhaNova());
		
		String senhaEncode = customPasswordEncoder.encode(trocaSenhaTO.getSenhaNova());
		acessoDao.trocarSenha(usuario.getUsername(), senhaEncode);
		
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}
