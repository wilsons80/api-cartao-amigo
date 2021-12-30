package br.com.cartaoamigo.cmd.gateway.pagseguro.checkouttransparente;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TipoPlanoRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;
import br.com.cartaoamigo.rule.ValidarCamposObrigatoriosCobrancaCRPagSeguroRule;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.pagamento.PagamentoCheckoutTransparenteCartaoCreditoService;
import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoPagamentoCheckoutTransparenteCartaoCreditoTO;

@Component
public class PagamentoCartaoCreditoCmd {

	@Autowired private PagamentoCheckoutTransparenteCartaoCreditoService service;
	@Autowired private TipoPlanoRepository tipoPlanoRepository;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private ValidarCamposObrigatoriosCobrancaCRPagSeguroRule rule;
	
	
	public RetornoPagamentoCheckoutTransparenteCartaoCreditoTO realizarCheckoutTransparente(PagamentoCheckoutTransparenteCartaoCreditoTO to) {
		try {
			if(Objects.isNull(to.getIdPlano())) {
				throw new NotFoundException("Escolha o tipo de plano para realizar o pagamento.");
			}
			
			Optional<TipoPlano> tipoPlano = tipoPlanoRepository.findById(to.getIdPlano());
			if(!tipoPlano.isPresent()) {
				throw new NotFoundException("O tipo de plano escolhido não existe: " + to.getIdPlano());
			}
			
			to.setDescricaoPlano(tipoPlano.get().getDescricao());
			to.setValorPlano    (tipoPlano.get().getValor());
			
			
			// Verificar se o endereço de cobrando está preenchido
			Optional<PessoaFisica> comprador = pessoaFisicaRepository.findByCpf(to.getCpfComprador());
			if(!comprador.isPresent()) {
				throw new PessoaFisicaNaoEncontradaException("Não foi possível recuperar os dados do comprador.");
			}
			
			//Tratar os campos de cobrança
			rule.validar(comprador.get().getCidade(), 
					     comprador.get().getEndereco(), 
					     comprador.get().getNumeroEndereco(), 
					     comprador.get().getBairro(), 
					     comprador.get().getCep(), 
					     comprador.get().getUf());
			
			to.setCidadeCobranca     (comprador.get().getCidade());
			to.setEnderecoCobranca   (comprador.get().getEndereco());
			to.setNumeroCobranca     (comprador.get().getNumeroEndereco());
			to.setComplementoCobranca("");
			to.setDistritoCobranca   (comprador.get().getBairro());
			to.setCodPostalCobranca  (comprador.get().getCep().toString());
			to.setEstadoCobranca     (comprador.get().getUf());
	        
			to.setReference(tipoPlano.get().getId()+"_"+tipoPlano.get().getTipoPagamento());
			
			return service.realizarPagamentoCheckoutTransparente(to);
		} catch (Exception e) {
			throw new PagSeguroException("Ocorreu um erro ao realizar o pagamento:  " + e.getMessage());
		}
		
		
	}
	
}
