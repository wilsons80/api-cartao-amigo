package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.FormaPagamento;
import br.com.cartaoamigo.to.FormaPagamentoTO;

@Component
public class FormaPagamentoTOBuilder {
	
	public FormaPagamentoTO buildTO(FormaPagamento p) {
		FormaPagamentoTO to = new FormaPagamentoTO();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}
	
	public FormaPagamento build(FormaPagamentoTO p) {
		FormaPagamento to = new FormaPagamento();
		BeanUtils.copyProperties(p, to);
		
		return to;
	}

	
	public List<FormaPagamento> buildTOAll(List<FormaPagamentoTO> dtos) {
		return dtos.stream().map(dto-> build(dto)).collect(Collectors.toList());
	}
	
	public List<FormaPagamentoTO> buildTO(List<FormaPagamento> dtos) {
		return dtos.stream().map(dto-> buildTO(dto)).collect(Collectors.toList());
	}
	
}	
