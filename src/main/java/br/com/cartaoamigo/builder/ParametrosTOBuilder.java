package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Parametros;
import br.com.cartaoamigo.to.ParametrosTO;

@Component
public class ParametrosTOBuilder {

	public Parametros build(ParametrosTO p) {
		Parametros retorno = new Parametros();

		BeanUtils.copyProperties(p,  retorno);
		
		return retorno;
	}

	public ParametrosTO buildTO(Parametros p) {
		ParametrosTO retorno = new ParametrosTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p,  retorno);
		return retorno;
	}

	public List<ParametrosTO> buildAll(List<Parametros> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
