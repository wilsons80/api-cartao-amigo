package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.to.TipoPlanoTO;

@Component
public class TipoPlanoTOBuilder {
	
	public TipoPlanoTO buildTO(TipoPlano p) {
		TipoPlanoTO	to = new TipoPlanoTO();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public TipoPlano build(TipoPlanoTO p) {
		TipoPlano to = new TipoPlano();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public List<TipoPlano> buildTOAll(List<TipoPlanoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<TipoPlanoTO> buildAll(List<TipoPlano> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
