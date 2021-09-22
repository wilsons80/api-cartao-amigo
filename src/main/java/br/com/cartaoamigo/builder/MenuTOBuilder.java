package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.MenuDTO;
import br.com.cartaoamigo.to.MenuTO;


@Component
public class MenuTOBuilder {
	
	
	public MenuTO buildTO(MenuDTO dto) {
		MenuTO to = new MenuTO();
		
		to.setIdModulo(dto.getIdModulo());
		to.setNomeModulo(dto.getNomeModulo());
		
		return to;
	}
	
	public List<MenuTO> buildAll(List<MenuDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
