package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.to.CarteiraCartaoPagamentoAssociadoTO;

@Component
public class CarteiraCartaoPagamentoAssociadoTOBuilder {
	
	
	public CarteiraCartaoPagamentoAssociadoTO buildTO(CarteiraCartaoPagamentoAssociado p) {
		CarteiraCartaoPagamentoAssociadoTO to = new CarteiraCartaoPagamentoAssociadoTO();
		BeanUtils.copyProperties(p, to);
		return to;
	}
	
	public CarteiraCartaoPagamentoAssociado build(CarteiraCartaoPagamentoAssociadoTO p) {
		CarteiraCartaoPagamentoAssociado to = new CarteiraCartaoPagamentoAssociado();
		BeanUtils.copyProperties(p, to);
		return to;
	}
	
	public List<CarteiraCartaoPagamentoAssociado> buildTOAll(List<CarteiraCartaoPagamentoAssociadoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	public List<CarteiraCartaoPagamentoAssociadoTO> buildAll(List<CarteiraCartaoPagamentoAssociado> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
