package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetUsuarioLogadoCmd;
import br.com.cartaoamigo.entity.PerfilAmbienteSistema;
import br.com.cartaoamigo.to.PerfilAmbienteSistemaTO;

@Component
public class PerfilAmbienteSistemaTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	public PerfilAmbienteSistema build(PerfilAmbienteSistemaTO to) {
		PerfilAmbienteSistema entity = new PerfilAmbienteSistema();
		
		BeanUtils.copyProperties(to, entity);
		
		return entity;
	}
	
	public PerfilAmbienteSistemaTO buildTO(PerfilAmbienteSistema entity) {
		PerfilAmbienteSistemaTO to = new PerfilAmbienteSistemaTO ();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		return to;
	}
	
	public void buildEntity(PerfilAmbienteSistemaTO to, PerfilAmbienteSistema entity) {
		entity.setIdUsuario(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity.setFixedFooter(to.getFixedFooter());
		entity.setFixedHeader(to.getFixedHeader());
		entity.setFixedSidenav(to.getFixedSidenav());
		entity.setFixedSidenavUserContent(to.getFixedSidenavUserContent());
		entity.setId(to.getId());
		entity.setMenu(to.getMenu());
		entity.setMenuType(to.getMenuType());
		entity.setName(to.getName());
		entity.setRtl(to.getRtl());
		entity.setSidenavIsOpened(to.getSidenavIsOpened());
		entity.setSidenavIsPinned(to.getSidenavIsPinned());
		entity.setTheme(to.getTheme());
	}
	
	public List<PerfilAmbienteSistemaTO> buildAll(List<PerfilAmbienteSistema> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
}
