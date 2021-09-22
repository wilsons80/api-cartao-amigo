package br.com.cartaoamigo.cmd.pagseguro.split;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.AutorizacaoGatewayTOBuilder;
import br.com.cartaoamigo.cmd.SalvarAutorizacaoGatewayCmd;
import br.com.cartaoamigo.dao.repository.AutorizacaoGatewayRepository;
import br.com.cartaoamigo.entity.AutorizacaoGateway;
import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.to.AutorizacaoGatewayTO;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.split.autorizacao.AutorizacaoSplitService;
import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoTO;
import br.com.cartaoamigo.ws.pagseguro.to.AutorizacaoXMLTO;

@Component
public class GetAutorizacaoSplitCmd {

	@Autowired private AutorizacaoGatewayRepository repository;
	@Autowired private AutorizacaoGatewayTOBuilder toBuilder;
	@Autowired private AutorizacaoSplitService autorizacaoService;
	@Autowired private PagSeguroSplitProvider pagSeguroSplitProvider;
	@Autowired private SalvarAutorizacaoGatewayCmd salvarCmd;
	
	
	public List<AutorizacaoTO> getAll() {
		List<AutorizacaoGateway> lista = repository.findAll(Sort.by("dataAutorizacao").descending());
		if(lista != null) {
			return toBuilder.buildAllAutorizacaoTO(lista);
		}
		return null;
	}
	
	
	
	public AutorizacaoTO getAutorizacao() {
		AutorizacaoTO autorizacao = new AutorizacaoTO();
		try {
			AutorizacaoXMLTO xml = autorizacaoService.getAutorizacao();
			autorizacao.setCodigoAutorizacao(xml.getCode());
			autorizacao.setUrlAutorizacao(pagSeguroSplitProvider.getUrlDirecionarAutorizacao(xml.getCode()));
			
			AutorizacaoGatewayTO autorizacaoGatewayTO = salvarCmd.salvar(autorizacao);
			
			autorizacao.setCodigoAutorizacao(autorizacaoGatewayTO.getCodigoAutorizacao());
			autorizacao.setDataAutorizacao(autorizacaoGatewayTO.getDataAutorizacao());
			autorizacao.setUrlAutorizacao(autorizacaoGatewayTO.getUrl());
			
			return autorizacao;
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao salvar a autorização da aplicação: " + e.getMessage());
		}
	}

}
