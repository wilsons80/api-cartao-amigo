package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetCarteiraCartaoPagamentoAssociadoCmd;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.CarteiraCartaoPagamentoAssociado;
import br.com.cartaoamigo.entity.EntityBase;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;

@Component
public class HistoricoPagamentoTOBuilder {
	
	@Autowired private TitularTOBuilder titularTOBuilder;
	@Autowired private GatewayPagamentoTOBuilder gatewayPagamentoTOBuilder;
	@Autowired private FormaPagamentoTOBuilder formaPagamentoTOBuilder;
	@Autowired private TipoPlanoTOBuilder tipoPlanoTOBuilder;
	@Autowired private StatusTransacaoGatewayPagamentoTOBuilder statusTransacaoGatewayPagamentoTOBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private CarteiraCartaoPagamentoAssociadoTOBuilder cartaoPagamentoAssociadoTOBuilder;
	@Autowired private GetCarteiraCartaoPagamentoAssociadoCmd getCartaoPagamentoAssociadoCmd;
	
	
	public HistoricoPagamentoTO buildTO(HistoricoPagamento p) {
		HistoricoPagamentoTO to = new HistoricoPagamentoTO();
		
		BeanUtils.copyProperties(p,to);
		EntityBase.beanPropertiesToUpperCase(to);
	
		to.setGatewayPagamento(gatewayPagamentoTOBuilder.buildTO(p.getGatewayPagamento()));
		to.setFormaPagamento(formaPagamentoTOBuilder.buildTO(p.getFormaPagamento()));
		to.setTipoPlano(tipoPlanoTOBuilder.buildTO(p.getTipoPlano()));
		to.setStatusTransacao(statusTransacaoGatewayPagamentoTOBuilder.buildTO(p.getStatusTransacao()));
		to.setTitular(titularTOBuilder.buildTO(p.getTitular()));
		
		if(Objects.nonNull(p.getCorretor()) && Objects.nonNull(p.getCorretor().getId())) {
			to.setCorretor(pessoaFisicaTOBuilder.buildTO(p.getCorretor()));
		}

		if(Objects.nonNull(p.getCartaoPagamento()) && Objects.nonNull(p.getCartaoPagamento().getId())) {
			to.setCartaoPagamento(cartaoPagamentoAssociadoTOBuilder.buildTO(p.getCartaoPagamento()));
		}

		return to;
	}
	
	public HistoricoPagamento build(HistoricoPagamentoTO p) {
		HistoricoPagamento to = new HistoricoPagamento();
		
		BeanUtils.copyProperties(p,to);
		EntityBase.beanPropertiesToUpperCase(to);
		
		to.setGatewayPagamento(gatewayPagamentoTOBuilder.build(p.getGatewayPagamento()));
		to.setFormaPagamento(formaPagamentoTOBuilder.build(p.getFormaPagamento()));
		to.setTipoPlano(tipoPlanoTOBuilder.build(p.getTipoPlano()));
		to.setStatusTransacao(statusTransacaoGatewayPagamentoTOBuilder.build(p.getStatusTransacao()));
		to.setTitular(titularTOBuilder.build(p.getTitular()));
		
		if(Objects.nonNull(p.getCorretor()) && Objects.nonNull(p.getCorretor().getId())) {
			PessoaFisica corretor = pessoaFisicaRepository.findById(p.getCorretor().getId()).orElseThrow(() -> new NotFoundException("Corretor informado não existe."));
			corretor = pessoaFisicaTOBuilder.build(p.getCorretor());
			to.setCorretor(corretor);
		}

		if(Objects.nonNull(p.getCartaoPagamento()) && Objects.nonNull(p.getCartaoPagamento().getId())) {
			CarteiraCartaoPagamentoAssociado cartaoPagamento = getCartaoPagamentoAssociadoCmd.getById(p.getCartaoPagamento().getId());
			to.setCartaoPagamento(cartaoPagamento);
		}

		return to;
	}
	
	public List<HistoricoPagamento> buildTOAll(List<HistoricoPagamentoTO> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
	public List<HistoricoPagamentoTO> buildAll(List<HistoricoPagamento> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
}
