package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.TipoCargo;
import br.com.cartaoamigo.to.TipoCargoTO;


@Component
public class TipoCargoTOBuilder {
	
	public TipoCargoTO buildTO(TipoCargo p) {
		TipoCargoTO to = new TipoCargoTO();
		BeanUtils.copyProperties(p,  to);
		return to;
	}

	public TipoCargo build(TipoCargoTO p) {
		TipoCargo to = new TipoCargo();
		
		if (Objects.isNull(p)) {
			return to;
		}
		BeanUtils.copyProperties(p,  to);
		return to;
	}

	
	public List<TipoCargo> buildTOAll(List<TipoCargoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<TipoCargoTO> buildAll(List<TipoCargo> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
