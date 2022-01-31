package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.FormaPagamentoRepository;
import br.com.cartaoamigo.entity.Assinaturas;
import br.com.cartaoamigo.entity.FormaPagamento;
import br.com.cartaoamigo.to.AssinaturasTO;

@Component
public class AssinaturasTOBuilder {
	
	@Autowired private FormaPagamentoRepository formaPagamentoRepository;
	@Autowired private FormaPagamentoTOBuilder formaPagamentoTOBuilder;
	
	public AssinaturasTO buildTO(Assinaturas p) {
		AssinaturasTO	to = new AssinaturasTO();
		BeanUtils.copyProperties(p, to);
		
		if(Objects.nonNull(p.getFormaPagamento()) && Objects.nonNull(p.getFormaPagamento().getId())) {
			to.setFormaPagamento(formaPagamentoTOBuilder.buildTO(p.getFormaPagamento()));
		}
		
		return to;
	}
	
	public Assinaturas build(AssinaturasTO p) {
		Assinaturas to = new Assinaturas();
		BeanUtils.copyProperties(p, to);
		
		if(Objects.nonNull(p.getFormaPagamento()) && Objects.nonNull(p.getFormaPagamento().getId())) {
			Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(p.getFormaPagamento().getId());
			to.setFormaPagamento(formaPagamento.orElse(null));
		}
		
		return to;
	}
	
	public List<Assinaturas> buildTOAll(List<AssinaturasTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<AssinaturasTO> buildAll(List<Assinaturas> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
