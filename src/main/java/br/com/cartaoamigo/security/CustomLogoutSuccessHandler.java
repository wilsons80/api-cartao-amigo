package br.com.cartaoamigo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.SaveUsuarioLogadoCmd;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired private SaveUsuarioLogadoCmd saveUsuarioLogadoCmd;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		if (authentication != null) {
			saveUsuarioLogadoCmd.reset(authentication.getName());
			authentication.setAuthenticated(false);
		}
	}
}
