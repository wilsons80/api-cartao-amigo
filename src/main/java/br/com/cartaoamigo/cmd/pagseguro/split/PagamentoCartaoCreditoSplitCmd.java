package br.com.cartaoamigo.cmd.pagseguro.split;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
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
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;
import br.com.cartaoamigo.exception.VoucherInvalidoException;
import br.com.cartaoamigo.infra.util.Java8DateUtil;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.rule.ValidarCamposObrigatoriosCobrancaCRPagSeguroRule;
import br.com.cartaoamigo.rule.ValidarDadosCartaoCRPagSeguroRule;
import br.com.cartaoamigo.rule.VoucherAtivoRule;
import br.com.cartaoamigo.to.GatewayPagamentoTO;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;
import br.com.cartaoamigo.ws.pagseguro.split.pagamento.PagamentoSplitCartaoCreditoService;
import br.com.cartaoamigo.ws.pagseguro.to.CheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.CondicaoParcelamentoTO;
import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.ParcelasBandeiraTO;
import br.com.cartaoamigo.ws.pagseguro.to.PaymentMethodSplitTO;
import br.com.cartaoamigo.ws.pagseguro.to.PrimaryReceiverTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoTO;

@Component
public class PagamentoCartaoCreditoSplitCmd {
	
	private static final String PAGSEGURO      = "PAGSEGURO";
	private static final String CARTAO_CREDITO = "CARTAO_CREDITO";

	@Autowired private PagamentoSplitCartaoCreditoService service;
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
	@Autowired private GetCondicoesParcelamentoSplitCmd getCondicoesParcelamentoSplitCmd;
	@Autowired private SalvarValidadeCartaoCmd salvarValidadeCartaoCmd;
	@Autowired private ValidarDadosCartaoCRPagSeguroRule validarDadosCartaoCRPagSeguroRule;
	
	public RetornoSplitPagamentoTO realizarCheckoutTransparente(CheckoutTransparenteCartaoCreditoTO checkoutPagamentoTO) {
		try {
			PagamentoCheckoutTransparenteCartaoCreditoTO pagamentoCheckoutTransparenteCartaoCreditoTO = new PagamentoCheckoutTransparenteCartaoCreditoTO();
			HistoricoPagamentoTO historicoPagamentoTO = new HistoricoPagamentoTO();
			
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
			
			AtomicInteger quantidadeParcelas = new AtomicInteger();
			quantidadeParcelas.set(tipoPlano.get().getQuantidadeParcelas().intValue());
			
			
			ParcelasBandeiraTO parcelasBandeiraTO = new ParcelasBandeiraTO();
			
			if(valorCobrado > 0 && quantidadeParcelas.get() > 1) {
				CondicaoParcelamentoTO condicaoParcelamentoTO = getCondicoesParcelamentoSplitCmd.getCondicoesParcelamento(checkoutPagamentoTO.getIdSessao(), valorCobrado, checkoutPagamentoTO.getBandeiraCartao(), quantidadeParcelas.longValue());
				if(condicaoParcelamentoTO.getError()) {
					throw new PagSeguroException("Erro ao obter o valor da parcela.");
				}
				if(Objects.isNull(condicaoParcelamentoTO.getParcelas())) {
					throw new PagSeguroException("Erro ao obter os valores das parcelas.");
				}
				
				Long maxParcelamentoPermitido = condicaoParcelamentoTO.getParcelas().stream().map(p -> p.getQuantity()).mapToLong(v -> v).max().orElse(0);
				if(maxParcelamentoPermitido < tipoPlano.get().getQuantidadeParcelas()) {
					throw new PagSeguroException("A quantidade de parcelas está inferiar ao permitido pelo plano.");
				}
				
				//parcelasBandeiraTO = condicaoParcelamentoTO.getParcelas().stream().reduce((first, second) -> second).orElse(null);
				parcelasBandeiraTO = condicaoParcelamentoTO.getParcelas().stream().filter(p -> p.getQuantity().equals(quantidadeParcelas.get())).findFirst().orElse(null);
			} else {
				parcelasBandeiraTO.setInstallmentAmount(valorCobrado);
			}
			
			
			pagamentoCheckoutTransparenteCartaoCreditoTO.setIdPlano            (tipoPlano.get().getId());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setDescricaoPlano     (tipoPlano.get().getDescricao());
			
			pagamentoCheckoutTransparenteCartaoCreditoTO.setValorPlano         (valorCobrado);
			pagamentoCheckoutTransparenteCartaoCreditoTO.setValorParcela       (parcelasBandeiraTO.getInstallmentAmount());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setQtdParcelas        (quantidadeParcelas.get());	
			pagamentoCheckoutTransparenteCartaoCreditoTO.setQtdParcelasSemJuros(quantidadeParcelas.incrementAndGet());
			
			pagamentoCheckoutTransparenteCartaoCreditoTO.setSenderHash         (checkoutPagamentoTO.getSenderHash());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setTokenCartaoCredito (checkoutPagamentoTO.getTokenCartaoCredito());
			
			
			// Busca os dados do comprador (Associado)
			Optional<PessoaFisica> comprador = pessoaFisicaRepository.findByCpf( NumeroUtil.somenteNumeros(checkoutPagamentoTO.getCpfComprador()));
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
			
			// Dados do comprador
			String celular = NumeroUtil.somenteNumeros(comprador.get().getCelular());
			
			pagamentoCheckoutTransparenteCartaoCreditoTO.setNomeComprador      (comprador.get().getNome());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setCpfComprador       (comprador.get().getCpf());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setCodAreaComprador   (celular.substring(0,2));
			pagamentoCheckoutTransparenteCartaoCreditoTO.setTelefoneComprador  (celular.substring(2));
			pagamentoCheckoutTransparenteCartaoCreditoTO.setEmailComprador     (comprador.get().getEmail());

			validarDadosCartaoCRPagSeguroRule.validar(checkoutPagamentoTO.getNomeImpressoCartao(), 
					                                  checkoutPagamentoTO.getCpfTitularCartao(), 
					                                  checkoutPagamentoTO.getDataNascimentoTitularCartao());
			
			pagamentoCheckoutTransparenteCartaoCreditoTO.setNomeImpressoCartao   (checkoutPagamentoTO.getNomeImpressoCartao());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setCpfTitularCartao     (NumeroUtil.somenteNumeros(checkoutPagamentoTO.getCpfTitularCartao()));
			pagamentoCheckoutTransparenteCartaoCreditoTO.setDataNascimentoCartao (Java8DateUtil.getLocalDateFormater(checkoutPagamentoTO.getDataNascimentoTitularCartao()));
			pagamentoCheckoutTransparenteCartaoCreditoTO.setCodAreaTitularCartao (celular.substring(0,2));
			pagamentoCheckoutTransparenteCartaoCreditoTO.setTelefoneTitularCartao(celular.substring(2));

			
			pagamentoCheckoutTransparenteCartaoCreditoTO.setCidadeCobranca     (comprador.get().getCidade());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setEnderecoCobranca   (comprador.get().getEndereco());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setNumeroCobranca     (comprador.get().getNumeroEndereco());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setComplementoCobranca("");
			pagamentoCheckoutTransparenteCartaoCreditoTO.setDistritoCobranca   (comprador.get().getBairro());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setCodPostalCobranca  (comprador.get().getCep().toString());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setEstadoCobranca     (comprador.get().getUf());
			pagamentoCheckoutTransparenteCartaoCreditoTO.setReference          ("CA_CR_"+comprador.get().getId());
			
			Optional<Titular> titular = getTitularCmd.getByPessoaFisica(comprador.get().getId());
			
			String codigoCorretor = checkoutPagamentoTO.getCodigoCorretor();
			if(StringUtils.isNotEmpty(codigoCorretor)){
				titular.get().setCodigoCorretor(codigoCorretor);
				titularRepository.save(titular.get());
			} else {
				codigoCorretor = titular.get().getCodigoCorretor();
			}
			
			//Preenche os dados do corretor
			pagamentoCheckoutTransparenteCartaoCreditoTO.setIsPorcentagemCorretor(false);
			if(StringUtils.isNotEmpty(codigoCorretor) && valorCobrado > 0) {
				Optional<Corretor> corretor = corretorRepository.findByCodigo(codigoCorretor);
				if(!corretor.isPresent()) {
					throw new NotFoundException("Não existe corretor cadastrado com o código: " + codigoCorretor);
				}
				
				// Não permite que o corretor e o associado seja a mesma pessoa   
				if(corretor.get().getPessoaFisica().getId().equals(comprador.get().getId())) {
					throw new PagSeguroException("Não é possível usar o próprio código de corretor no pagamento.");
				}
				
				if(StringUtils.isEmpty(corretor.get().getPublicKey())) {
					throw new PagSeguroException("Corretor ainda não foi autorizado pelo sistema de pagamento.");
				}

				pagamentoCheckoutTransparenteCartaoCreditoTO.setIsPorcentagemCorretor(true);
				if(corretor.get().getIsPorcentagem()) {
					Double valor = (valorCobrado * corretor.get().getValorRecebimento()) / 100;
					pagamentoCheckoutTransparenteCartaoCreditoTO.setValorCorretor(valor);
				} else {
					pagamentoCheckoutTransparenteCartaoCreditoTO.setValorCorretor(corretor.get().getValorRecebimento());	
				}
				
				pagamentoCheckoutTransparenteCartaoCreditoTO.setPublicKeyCorretor(corretor.get().getPublicKey());
				
				historicoPagamentoTO.setCorretor(pessoaFisicaTOBuilder.buildTO(corretor.get().getPessoaFisica()));
				historicoPagamentoTO.setValorCorretor(pagamentoCheckoutTransparenteCartaoCreditoTO.getValorCorretor());
			}
			
			
			RetornoSplitPagamentoCartaoCreditoTO retornoPagSeguroTO = null;
			try {
				if(voucher.isPresent() && voucher.get().getPorcentagem() >= 100) {
					retornoPagSeguroTO = new RetornoSplitPagamentoCartaoCreditoTO();
					retornoPagSeguroTO.setCode           (voucher.get().getId().toString());
					retornoPagSeguroTO.setPaymentMethod  (new PaymentMethodSplitTO());
					retornoPagSeguroTO.setStatus         ("3");					
					retornoPagSeguroTO.setPrimaryReceiver(new PrimaryReceiverTO());
					
					salvarValidadeCartaoCmd.incrementarValidade(comprador.get().getId(), tipoPlano.get().getQuantidadeParcelas().intValue());
					
				} else {
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					// Realiza a chamada do endpoint de pagamento do PAGSEGURO
					retornoPagSeguroTO = service.realizarPagamentoCheckoutTransparente(pagamentoCheckoutTransparenteCartaoCreditoTO);
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				}				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw new PagSeguroException("Não foi possível concluir o pagamento, por favor verifique os dados do seu cartão.");
			}
			
			
			historicoPagamentoTO.setId                             (null);
			historicoPagamentoTO.setDtPagamentoPlanoContratado     (LocalDateTime.now());
			historicoPagamentoTO.setLinkPagamento                  (retornoPagSeguroTO.getLinkPagamento());
			historicoPagamentoTO.setNumeroTransacaoGatewayPagamento(retornoPagSeguroTO.getCode());
			historicoPagamentoTO.setQtdParcelas                    (retornoPagSeguroTO.getInstallmentCount());
			historicoPagamentoTO.setTipoMetodoPagamento            (retornoPagSeguroTO.getPaymentMethod().getType());
			historicoPagamentoTO.setTitular                        (titularTOBuilder.buildTO(titular.get()));	
			
			if(voucher.isPresent()) {
				historicoPagamentoTO.setIdVoucher(voucher.get().getId());
				
				voucher.get().setUtilizado(true);
				voucher.get().setDataUtilizacao(LocalDateTime.now());
				voucher.get().setIdPessoaFisica(comprador.get().getId());
				
				voucherRepository.save(voucher.get());
			}			
			
			GatewayPagamentoTO gatewayPagamentoTO = getGatewayPagamentoCmd.getByCodigo(PAGSEGURO);
			StatusTransacaoGatewayPagamentoTO statusTO = getStatusTransacaoCmd.getByStatusAndGateway(Long.valueOf(retornoPagSeguroTO.getStatus()), gatewayPagamentoTO.getId());

			historicoPagamentoTO.setStatusTransacao    (statusTO);
			historicoPagamentoTO.setGatewayPagamento   (gatewayPagamentoTO);
			
			
			Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findByNome(CARTAO_CREDITO);
			historicoPagamentoTO.setFormaPagamento(formaPagamentoTOBuilder.buildTO(formaPagamento.get()));
			
			historicoPagamentoTO.setPublicKeyPrimaryReceiver(retornoPagSeguroTO.getPrimaryReceiver().getPublicKey());
			historicoPagamentoTO.setTipoPlano(tipoPlanoTOBuilder.buildTO(tipoPlano.get()));
			historicoPagamentoTO.setValorPago(valorCobrado);
			
			
			historicoPagamentoTO = cadastrarHistoricoPagamentoCmd.cadastrar(historicoPagamentoTO);
			
			RetornoSplitPagamentoTO retorno = new RetornoSplitPagamentoTO();
			retorno.setCodigoTransacao         (retornoPagSeguroTO.getCode());
			retorno.setLinkPagamento           (null);
			retorno.setStatusTransacao         (statusTO.getCodigoTransacao().toString());
			retorno.setDescricaoStatusTransacao(statusTO.getDescricao());
			return retorno;
			
		} catch (Exception e) {
			throw new PagSeguroException(e.getMessage());
		}
		
		
	}


	
}
