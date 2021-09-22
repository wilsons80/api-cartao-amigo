package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.to.CorretorTO;

@Component
public class CorretorTOBuilder {
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;

	public CorretorTO buildTO(Corretor p) {
		CorretorTO to = new CorretorTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setLinkPagamento(to.getLinkPagamento().toLowerCase());
		to.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		
		return to;
	}
	
	public Corretor build(CorretorTO p) {
		Corretor to = new Corretor();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		if(StringUtils.isNotEmpty(to.getLinkPagamento())) {
			to.setLinkPagamento(to.getLinkPagamento().toLowerCase());
		}
		to.setPessoaFisica(pessoaFisicaTOBuilder.build(p.getPessoaFisica()));
		
		return to;
	}
	
	public List<Corretor> buildTOAll(List<CorretorTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<CorretorTO> buildAll(List<Corretor> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
		
	}
}
