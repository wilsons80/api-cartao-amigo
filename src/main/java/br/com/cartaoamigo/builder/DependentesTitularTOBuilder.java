package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetCartaoCmd;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.to.DependentesTitularTO;

@Component
public class DependentesTitularTOBuilder {
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetCartaoCmd getCartaoCmd;
	
	
	public DependentesTitular buildNewEntity(DependentesTitularTO to, DependentesTitular entity) {
		BeanUtils.copyProperties(to, entity);
		EntityBase.beanPropertiesToUpperCase(entity);
		
		return entity;
	} 
	
	
	public DependentesTitularTO buildTO(DependentesTitular p) {
		DependentesTitularTO to = new DependentesTitularTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		if(Objects.isNull(p)) {
			return to;
		}
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		to.setCartao(getCartaoCmd.getCartaoDependenteByIdPessoaFisica(to.getPessoaFisica().getId()));
		
		to.setIdTitular(p.getIdTitular());
		
		return to;
		}
		
	public DependentesTitular build(DependentesTitularTO p) {
		DependentesTitular to = new DependentesTitular();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.build(p.getPessoaFisica()));
		to.setIdTitular(p.getIdTitular());
		
		
		return to;
	}
	
	public List<DependentesTitular> buildTOAll(List<DependentesTitularTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<DependentesTitularTO> buildAll(List<DependentesTitular> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
