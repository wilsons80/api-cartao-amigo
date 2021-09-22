package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.AssociadoComboDTO;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.to.ComboAssociadoTO;
import br.com.cartaoamigo.to.PessoaFisicaTO;

@Component
public class PessoaFisicaTOBuilder {

	
	public PessoaFisica buildNewEntity(PessoaFisicaTO to, PessoaFisica entity) {
		BeanUtils.copyProperties(to, entity, "id");
		EntityBase.beanPropertiesToUpperCase(entity);
		return entity;
	} 
	
	public PessoaFisicaTO buildNewTO(PessoaFisica entity, PessoaFisicaTO to) {
		BeanUtils.copyProperties(entity, to, "email");
		EntityBase.beanPropertiesToUpperCase(to);
	
		return to;
	} 
	
	public PessoaFisica build(PessoaFisicaTO p) {
		PessoaFisica retorno = new PessoaFisica();

		BeanUtils.copyProperties(p, retorno);
		EntityBase.beanPropertiesToUpperCase(retorno);
		
		return retorno;
	}

	public PessoaFisicaTO buildTO(PessoaFisica p) {
		PessoaFisicaTO retorno = new PessoaFisicaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		EntityBase.beanPropertiesToUpperCase(retorno);
		
		return retorno;
	}

	public PessoaFisicaTO buildParaCombo(PessoaFisica p) {
		PessoaFisicaTO to = new PessoaFisicaTO();
		
		if(Objects.isNull(p)) {
			return to;
		}
		
		to.setId(p.getId());
		to.setNome(p.getNome());
		to.setCpf(p.getCpf().substring(0, 3) + "***.***-" + p.getCpf().substring(9));
		
		return to;
		
	}
	
	public ComboAssociadoTO buildComboAssociadoTO(AssociadoComboDTO p) {
		ComboAssociadoTO retorno = new ComboAssociadoTO();
		
		if(Objects.isNull(p)) {return retorno;}		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}
	
	public List<PessoaFisicaTO> buildAll(List<PessoaFisica> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<ComboAssociadoTO> buildAllComboAssociadoDTO(List<AssociadoComboDTO> dtos) {
		return dtos.stream().map(dto -> buildComboAssociadoTO(dto)).collect(Collectors.toList());
	}

}
