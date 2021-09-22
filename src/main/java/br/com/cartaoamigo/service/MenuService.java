package br.com.cartaoamigo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.GetMenuCmd;
import br.com.cartaoamigo.to.MenuTO;

@RestController
@RequestMapping(value = "menu")
public class MenuService {
	
	@Autowired
	private GetMenuCmd getMenuCmd;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuTO> getMenuPrincipal() {
		return getMenuCmd.getMenuPrincipal();
	}

}
