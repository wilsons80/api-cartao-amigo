package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.ImpressaoCartaoDTO;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.to.relatorios.ImpressaoCartaoTO;

@Component
public class ImpressaoCartaoTOBuilder {
	
	public ImpressaoCartaoTO buildComboTO(ImpressaoCartaoDTO p) {
		ImpressaoCartaoTO retorno = new ImpressaoCartaoTO();
		
		if(Objects.isNull(p)) {return retorno;}		
		BeanUtils.copyProperties(p, retorno);
		EntityBase.beanPropertiesToUpperCase(retorno);
		
		return retorno;
	}

	public List<ImpressaoCartaoTO> buildAllDTO(List<ImpressaoCartaoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

}
