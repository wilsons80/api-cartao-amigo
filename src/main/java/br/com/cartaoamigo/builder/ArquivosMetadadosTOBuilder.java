package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.ArquivosMetadados;
import br.com.cartaoamigo.to.ArquivosMetadadosTO;


@Component
public class ArquivosMetadadosTOBuilder {
	
	public ArquivosMetadadosTO buildTO(ArquivosMetadados p) {
		ArquivosMetadadosTO to = new ArquivosMetadadosTO();
		
		BeanUtils.copyProperties(p,  to);
		
		return to;
	}

	public ArquivosMetadados build(ArquivosMetadadosTO p) {
		ArquivosMetadados to = new ArquivosMetadados();
		
		if (Objects.isNull(p)) {
			return null;
		}
		
		BeanUtils.copyProperties(p,  to);
		
		return to;
	}

	
	public List<ArquivosMetadados> buildTOAll(List<ArquivosMetadadosTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<ArquivosMetadadosTO> buildAll(List<ArquivosMetadados> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
