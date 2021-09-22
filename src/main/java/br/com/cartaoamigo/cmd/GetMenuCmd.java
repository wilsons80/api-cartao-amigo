package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MenuTOBuilder;
import br.com.cartaoamigo.dao.MenuDao;
import br.com.cartaoamigo.dao.dto.MenuDTO;
import br.com.cartaoamigo.rule.VerificaParametrosAcessoRule;
import br.com.cartaoamigo.rule.VerificaPermissaoMenuRule;
import br.com.cartaoamigo.to.MenuTO;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@Component
public class GetMenuCmd {
	
	@Autowired private MenuDao menuDao ;
	@Autowired private MenuTOBuilder menuTOBuilder;
	@Autowired private VerificaParametrosAcessoRule verificaParametrosAcessoRule;
	@Autowired private VerificaPermissaoMenuRule verificaPermissaoMenuRule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	public List<MenuTO> getMenuPrincipal() {
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		String username = usuarioLogado.getUsername();
		
		verificaParametrosAcessoRule.verificar(username);
		List<MenuDTO> menu = menuDao.getMenuPrincipal(usuarioLogado.getIdUsuario());
		
		verificaPermissaoMenuRule.verificar(menu);
		
		return menuTOBuilder.buildAll(menu);
	}

}
