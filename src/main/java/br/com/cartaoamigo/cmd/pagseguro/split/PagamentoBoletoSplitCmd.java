package br.com.cartaoamigo.cmd.pagseguro.split;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.FormaPagamentoTOBuilder;
import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.builder.TipoPlanoTOBuilder;
import br.com.cartaoamigo.builder.TitularTOBuilder;
import br.com.cartaoamigo.cmd.CadastrarHistoricoPagamentoCmd;
import br.com.cartaoamigo.cmd.GetGatewayPagamentoCmd;
import br.com.cartaoamigo.cmd.GetTitularCmd;
import br.com.cartaoamigo.cmd.SalvarValidadeCartaoCmd;
import br.com.cartaoamigo.cmd.pagseguro.GetStatusTransacaoCmd;
import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.dao.repository.FormaPagamentoRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TipoPlanoRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.dao.repository.VoucherRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.FormaPagamento;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.exception.CorretorInvalidoException;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;
import br.com.cartaoamigo.exception.VoucherInvalidoException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.rule.ValidarCamposObrigatoriosCobrancaCRPagSeguroRule;
import br.com.cartaoamigo.rule.VoucherAtivoRule;
import br.com.cartaoamigo.to.GatewayPagamentoTO;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;
import br.com.cartaoamigo.ws.pagseguro.split.pagamento.PagamentoSplitBoletoService;
import br.com.cartaoamigo.ws.pagseguro.to.CheckoutTransparenteBoletoTO;
import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteBoletoTO;
import br.com.cartaoamigo.ws.pagseguro.to.PaymentMethodSplitTO;
import br.com.cartaoamigo.ws.pagseguro.to.PrimaryReceiverTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoBoletoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoTO;

@Component
public class PagamentoBoletoSplitCmd {
	
	private static final String PAGSEGURO      = "PAGSEGURO";
	private static final String BOLETO         = "BOLETO";
	private static final Logger LOGGER=LoggerFactory.getLogger(PagamentoBoletoSplitCmd.class);
	
	@Autowired private PagamentoSplitBoletoService service;
	@Autowired private TipoPlanoRepository tipoPlanoRepository;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private ValidarCamposObrigatoriosCobrancaCRPagSeguroRule rule;
	@Autowired private CorretorRepository corretorRepository;
	@Autowired private CadastrarHistoricoPagamentoCmd cadastrarHistoricoPagamentoCmd;
	@Autowired private TipoPlanoTOBuilder tipoPlanoTOBuilder;
	@Autowired private GetTitularCmd getTitularCmd;
	@Autowired private TitularTOBuilder titularTOBuilder;
	@Autowired private TitularRepository titularRepository;
	@Autowired private GetStatusTransacaoCmd getStatusTransacaoCmd;	
	@Autowired private GetGatewayPagamentoCmd getGatewayPagamentoCmd;
	@Autowired private FormaPagamentoRepository formaPagamentoRepository;
	@Autowired private FormaPagamentoTOBuilder formaPagamentoTOBuilder;
	@Autowired private VoucherRepository voucherRepository;
	@Autowired private VoucherAtivoRule voucherAtivoRule;
	@Autowired private SalvarValidadeCartaoCmd salvarValidadeCartaoCmd;
	
	
	public RetornoSplitPagamentoTO realizarCheckoutTransparente(CheckoutTransparenteBoletoTO checkoutPagamentoTO) {
		HistoricoPagamentoTO historicoPagamentoTO = new HistoricoPagamentoTO();
		PagamentoCheckoutTransparenteBoletoTO pagamentoCheckoutTransparenteBoletoTO = new PagamentoCheckoutTransparenteBoletoTO();
		
		if(Objects.isNull(checkoutPagamentoTO.getIdPlano())) {
			throw new NotFoundException("Escolha o tipo de plano para realizar o pagamento.");
		}
		
		Optional<TipoPlano> tipoPlano = tipoPlanoRepository.findById(checkoutPagamentoTO.getIdPlano());
		if(!tipoPlano.isPresent()) {
			throw new NotFoundException("O tipo de plano escolhido não existe: " + checkoutPagamentoTO.getIdPlano());
		}
		
		Double valorCobrado = tipoPlano.get().getValor();
		if(valorCobrado < 1.00) {
			throw new PagSeguroException("Valor da compra está inferior ao permitido.");
		}
		
		Optional<Voucher> voucher = Optional.empty();
		if(StringUtils.isNotEmpty(checkoutPagamentoTO.getVoucher())) {
			voucher = voucherRepository.findByCodigo(checkoutPagamentoTO.getVoucher());
			if(voucher.isPresent()) {
				voucherAtivoRule.verificar(voucher.get());					
				double valorDesconto = (tipoPlano.get().getValor() * voucher.get().getPorcentagem()) / 100;
				valorCobrado = tipoPlano.get().getValor() - valorDesconto;
			} else {
				throw new VoucherInvalidoException("O código de cupom não existe em nossa base de dados."); 
			}
		}
		
		pagamentoCheckoutTransparenteBoletoTO.setIdPlano       (tipoPlano.get().getId());
		pagamentoCheckoutTransparenteBoletoTO.setDescricaoPlano(tipoPlano.get().getDescricao());
		pagamentoCheckoutTransparenteBoletoTO.setValorPlano    (valorCobrado);
		pagamentoCheckoutTransparenteBoletoTO.setSenderHash    (checkoutPagamentoTO.getSenderHash());
		
		// Verificar se o endereço de cobrando está preenchido
		Optional<PessoaFisica> pessoaFisicaComprador = pessoaFisicaRepository.findByCpf(checkoutPagamentoTO.getCpfComprador());
		if(!pessoaFisicaComprador.isPresent()) {
			throw new PessoaFisicaNaoEncontradaException("Não foi possível recuperar os dados do comprador.");
		}
		
		//Tratar os campos de cobrança
		rule.validar(pessoaFisicaComprador.get().getCidade(), 
				     pessoaFisicaComprador.get().getEndereco(), 
				     pessoaFisicaComprador.get().getNumeroEndereco(), 
				     pessoaFisicaComprador.get().getBairro(), 
				     pessoaFisicaComprador.get().getCep(), 
				     pessoaFisicaComprador.get().getUf());
		
		// Dados do comprador
		String celular = NumeroUtil.somenteNumeros(pessoaFisicaComprador.get().getCelular());
		
		pagamentoCheckoutTransparenteBoletoTO.setNomeComprador      (pessoaFisicaComprador.get().getNome());
		pagamentoCheckoutTransparenteBoletoTO.setCpfComprador       (pessoaFisicaComprador.get().getCpf());
		pagamentoCheckoutTransparenteBoletoTO.setCodAreaComprador   (celular.substring(0,2));
		pagamentoCheckoutTransparenteBoletoTO.setTelefoneComprador  (celular.substring(2));
		pagamentoCheckoutTransparenteBoletoTO.setEmailComprador     (pessoaFisicaComprador.get().getEmail());
		
		pagamentoCheckoutTransparenteBoletoTO.setCidadeCobranca     (pessoaFisicaComprador.get().getCidade());
		pagamentoCheckoutTransparenteBoletoTO.setEnderecoCobranca   (pessoaFisicaComprador.get().getEndereco());
		pagamentoCheckoutTransparenteBoletoTO.setNumeroCobranca     (pessoaFisicaComprador.get().getNumeroEndereco());
		pagamentoCheckoutTransparenteBoletoTO.setComplementoCobranca("");
		pagamentoCheckoutTransparenteBoletoTO.setDistritoCobranca   (pessoaFisicaComprador.get().getBairro());
		pagamentoCheckoutTransparenteBoletoTO.setCodPostalCobranca  (pessoaFisicaComprador.get().getCep().toString());
		pagamentoCheckoutTransparenteBoletoTO.setEstadoCobranca     (pessoaFisicaComprador.get().getUf());
		pagamentoCheckoutTransparenteBoletoTO.setReference          (tipoPlano.get().getId()+"_"+tipoPlano.get().getTipoPagamento());
		
		Optional<Titular> titular = getTitularCmd.getByPessoaFisica(pessoaFisicaComprador.get().getId());
		
		String codigoCorretor = checkoutPagamentoTO.getCodigoCorretor();
		if(StringUtils.isNotEmpty(codigoCorretor)){
			titular.get().setCodigoCorretor(codigoCorretor);
			titularRepository.save(titular.get());
		} else {
			codigoCorretor = titular.get().getCodigoCorretor();
		}
		
		//Preenche os dados do corretor
		pagamentoCheckoutTransparenteBoletoTO.setIsPorcentagemCorretor(false);
		if(StringUtils.isNotEmpty(codigoCorretor) && valorCobrado > 0) {
			Optional<Corretor> corretor = corretorRepository.findByCodigo(codigoCorretor);
			if(!corretor.isPresent()) {
				throw new NotFoundException("Não existe corretor cadastrado com o código: " + codigoCorretor);
			}
			
			// Não permite que o corretor e o associado seja a mesma pessoa   
			if(corretor.get().getPessoaFisica().getId().equals(pessoaFisicaComprador.get().getId())) {
				throw new CorretorInvalidoException("Não é possível usar o próprio código de corretor no pagamento.");
			}
			
			if(StringUtils.isEmpty(corretor.get().getPublicKey())) {
				throw new CorretorInvalidoException("Corretor ainda não foi autorizado pelo sistema de pagamento.");
			}
			
			pagamentoCheckoutTransparenteBoletoTO.setIsPorcentagemCorretor(true);
			if(corretor.get().getIsPorcentagem()) {
				Double valor = (valorCobrado * corretor.get().getValorRecebimento()) / 100;
				pagamentoCheckoutTransparenteBoletoTO.setValorCorretor(valor);
			} else {
				pagamentoCheckoutTransparenteBoletoTO.setValorCorretor(corretor.get().getValorRecebimento());	
			}
			pagamentoCheckoutTransparenteBoletoTO.setPublicKeyCorretor(corretor.get().getPublicKey());
			historicoPagamentoTO.setCorretor(pessoaFisicaTOBuilder.buildTO(corretor.get().getPessoaFisica()));
			historicoPagamentoTO.setValorCorretor(pagamentoCheckoutTransparenteBoletoTO.getValorCorretor());
		}
		
		pagamentoCheckoutTransparenteBoletoTO.setReference("CA_BO_"+pessoaFisicaComprador.get().getId());			
		
		RetornoSplitPagamentoBoletoTO retornoPagSeguroTO = null;
		if(voucher.isPresent() && voucher.get().getPorcentagem() >= 100) {
			retornoPagSeguroTO = new RetornoSplitPagamentoBoletoTO();
			retornoPagSeguroTO.setCode(voucher.get().getId().toString());
			retornoPagSeguroTO.setPaymentMethod(new PaymentMethodSplitTO());
			retornoPagSeguroTO.setStatus("3");					
			retornoPagSeguroTO.setPrimaryReceiver(new PrimaryReceiverTO());
			
			salvarValidadeCartaoCmd.incrementarValidade(pessoaFisicaComprador.get().getId(), tipoPlano.get().getQuantidadeParcelas().intValue());
			
		} else {
			try {
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// Realiza a chamada do endpoint de pagamento do PAGSEGURO
				LOGGER.info(">>>>> Dados pagamento: " + pagamentoCheckoutTransparenteBoletoTO.toString());
				retornoPagSeguroTO = service.realizarPagamentoCheckoutTransparente(pagamentoCheckoutTransparenteBoletoTO);
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			}catch (Exception e) {
				throw new PagSeguroException(e.getMessage());
			}
			
		}
			
		
		historicoPagamentoTO.setId                             (null);
		historicoPagamentoTO.setDtPagamentoPlanoContratado     (LocalDateTime.now());
		historicoPagamentoTO.setLinkPagamento                  (retornoPagSeguroTO.getPaymentLink());
		historicoPagamentoTO.setNumeroTransacaoGatewayPagamento(retornoPagSeguroTO.getCode());
		historicoPagamentoTO.setQtdParcelas                    (retornoPagSeguroTO.getInstallmentCount());
		historicoPagamentoTO.setTipoMetodoPagamento            (retornoPagSeguroTO.getPaymentMethod().getType());
		historicoPagamentoTO.setTitular                        (titularTOBuilder.buildTO(titular.get()));
		
		if(voucher.isPresent()) {
			historicoPagamentoTO.setIdVoucher(voucher.get().getId());
			
			voucher.get().setUtilizado(true);
			voucher.get().setDataUtilizacao(LocalDateTime.now());
			voucher.get().setIdPessoaFisica(pessoaFisicaComprador.get().getId());
			
			voucherRepository.save(voucher.get());
		}
		
		GatewayPagamentoTO gatewayPagamentoTO = getGatewayPagamentoCmd.getByCodigo(PAGSEGURO);
		StatusTransacaoGatewayPagamentoTO statusTO = getStatusTransacaoCmd.getByStatusAndGateway(Long.valueOf(retornoPagSeguroTO.getStatus()), gatewayPagamentoTO.getId());

		historicoPagamentoTO.setStatusTransacao    (statusTO);
		historicoPagamentoTO.setGatewayPagamento   (gatewayPagamentoTO);
		
		
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findByNome(BOLETO);
		historicoPagamentoTO.setFormaPagamento          (formaPagamentoTOBuilder.buildTO(formaPagamento.get()));
		historicoPagamentoTO.setPublicKeyPrimaryReceiver(retornoPagSeguroTO.getPrimaryReceiver().getPublicKey());
		historicoPagamentoTO.setTipoPlano               (tipoPlanoTOBuilder.buildTO(tipoPlano.get()));
		historicoPagamentoTO.setValorPago               (valorCobrado);
		
		cadastrarHistoricoPagamentoCmd.cadastrar(historicoPagamentoTO);
		
		RetornoSplitPagamentoTO retorno = new RetornoSplitPagamentoTO();
		retorno.setCodigoTransacao         (retornoPagSeguroTO.getCode());
		retorno.setLinkPagamento           (retornoPagSeguroTO.getPaymentLink());
		retorno.setStatusTransacao         (statusTO.getCodigoTransacao().toString());
		retorno.setDescricaoStatusTransacao(statusTO.getDescricao());
		return retorno;
		
		
		
		
	}

	
}
