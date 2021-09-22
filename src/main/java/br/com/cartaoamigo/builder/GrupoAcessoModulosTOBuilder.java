package br.com.cartaoamigo.builder;

	import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.to.GrupoAcessoModulosTO;


@Component
public class GrupoAcessoModulosTOBuilder {
	
	@Autowired private ModuloTOBuilder modulosTOBuilder;
	
	public GrupoAcessoModulosTO buildTO(GrupoAcessoModulos p) {
		GrupoAcessoModulosTO to = new GrupoAcessoModulosTO();
		BeanUtils.copyProperties(p,  to);
		to.setModulo(modulosTOBuilder.buildTO(p.getModulo()));
		
		return to;
	}

	public GrupoAcessoModulos build(GrupoAcessoModulosTO p) {
		GrupoAcessoModulos to = new GrupoAcessoModulos();
		if (Objects.isNull(p)) {
			return to;
		}
		BeanUtils.copyProperties(p,  to);
		to.setModulo(modulosTOBuilder.build(p.getModulo()));
		
		return to;
	}

	
	public List<GrupoAcessoModulos> buildTOAll(List<GrupoAcessoModulosTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<GrupoAcessoModulosTO> buildAll(List<GrupoAcessoModulos> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
