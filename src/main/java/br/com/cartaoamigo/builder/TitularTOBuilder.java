package br.com.cartaoamigo.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.to.TitularTO;

@Component
public class TitularTOBuilder {
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private DependentesTitularTOBuilder dependentesTitularTOBuilder;
	@Autowired private DependentesTitularRepository dependentesTitularRepository; 
	@Autowired private CartaoRepository cartaoRepository;
	
	public TitularTO buildTO(Titular p) {
		TitularTO to = new TitularTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		
		if(Objects.nonNull(p.getId())) {
			Optional<List<DependentesTitular>> entitys = dependentesTitularRepository.findAllByTitular(p.getId());
			if(entitys.isPresent()) {
				to.setDependentes(dependentesTitularTOBuilder.buildAll(entitys.get()));
				Optional<Cartao> cartaoTitular = cartaoRepository.findCartaoTitularByIdPessoaFisica(to.getPessoaFisica().getId());
				if(cartaoTitular.isPresent()) {
					to.getDependentes().forEach(dep -> dep.getCartao().setDataValidadePlano(cartaoTitular.get().getDataValidadePlano()));
				}
			}else {
				to.setDependentes(new ArrayList<>());
			}
		}
		
		return to;
	}
	
	public Titular build(TitularTO p) {
		Titular to = new Titular();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		PessoaFisica pessoaFisica = pessoaFisicaTOBuilder.build(p.getPessoaFisica());
		to.setPessoaFisica(pessoaFisica);
				
		return to;
	}
	
	public List<Titular> buildTOAll(List<TitularTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<TitularTO> buildAll(List<Titular> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
}
	}
