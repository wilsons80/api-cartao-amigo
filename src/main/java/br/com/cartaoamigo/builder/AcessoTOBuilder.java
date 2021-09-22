package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.AcessoDTO;
import br.com.cartaoamigo.to.AcessoTO;


@Component
public class AcessoTOBuilder {
	
	public AcessoTO buildTO(AcessoDTO dto) {
		AcessoTO to = new AcessoTO();
		
		BeanUtils.copyProperties(dto,  to);
		
		return to;
	}
	
	public List<AcessoTO> buildAll(List<AcessoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
