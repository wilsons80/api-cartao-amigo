package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.to.AssinaturasTO;

@Component
public class AssinaturasTOBuilder {
	
	public AssinaturasTO buildTO(Assinaturas p) {
		AssinaturasTO	to = new AssinaturasTO();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public Assinaturas build(AssinaturasTO p) {
		Assinaturas to = new Assinaturas();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public List<Assinaturas> buildTOAll(List<AssinaturasTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<AssinaturasTO> buildAll(List<Assinaturas> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
