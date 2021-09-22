package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.PerfilAcesso;
import br.com.cartaoamigo.to.PerfilAcessoTO;

@Component
public class PerfilAcessoTOBuilder {


	public PerfilAcesso build(PerfilAcessoTO to) {
		PerfilAcesso entity = new PerfilAcesso();
		
		BeanUtils.copyProperties(to, entity);

		return entity;
	}

	public PerfilAcessoTO buildTO(PerfilAcesso dto) {
		PerfilAcessoTO to = new PerfilAcessoTO();
		
		if(Objects.isNull(dto)) {
			return to;
		}
		
		BeanUtils.copyProperties(dto, to);

		return to;
	}

	public List<PerfilAcessoTO> buildAll(List<PerfilAcesso> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
