package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetHistoricoPagamentoCmd;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.to.CartaoTO;

@Component
public class CartaoTOBuilder {
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetHistoricoPagamentoCmd getHistoricoPagamentoCmd;
	
	public CartaoTO buildTO(Cartao p) {
		CartaoTO to = new CartaoTO();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		to.setUrlCode(to.getUrlCode().toLowerCase());
		
		if(Objects.nonNull(p.getPessoaFisica())) {
			to.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		}
		
		boolean possuiPagamentoAprovado = getHistoricoPagamentoCmd.isPossuiPagamentoAprovado(p.getIdTitular());
		to.setIsPagamentoRealizado(possuiPagamentoAprovado);
		
		return to;
	}
	public Cartao build(CartaoTO p) {
		Cartao to = new Cartao();
		BeanUtils.copyProperties(p, to);
		EntityBase.beanPropertiesToUpperCase(to);
		to.setUrlCode(to.getUrlCode().toLowerCase());
		
		to.setPessoaFisica(pessoaFisicaTOBuilder.build(p.getPessoaFisica()));

		return to;
	}
	
	public List<Cartao> buildTOAll(List<CartaoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	public List<CartaoTO> buildAll(List<Cartao> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
