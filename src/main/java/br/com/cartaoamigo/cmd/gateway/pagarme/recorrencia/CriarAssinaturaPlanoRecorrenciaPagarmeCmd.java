package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cartaoamigo.builder.FormaPagamentoTOBuilder;
import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.builder.TipoPlanoTOBuilder;
import br.com.cartaoamigo.builder.TitularTOBuilder;
import br.com.cartaoamigo.cmd.CadastrarHistoricoPagamentoCmd;
import br.com.cartaoamigo.cmd.GetAssinaturasCmd;
import br.com.cartaoamigo.cmd.GetGatewayPagamentoCmd;
import br.com.cartaoamigo.cmd.GetTitularCmd;
import br.com.cartaoamigo.cmd.SalvarAssinaturaCmd;
import br.com.cartaoamigo.cmd.gateway.GetStatusTransacaoCmd;
import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.dao.repository.FormaPagamentoRepository;
import br.com.cartaoamigo.dao.repository.TipoPlanoRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.dao.repository.VoucherRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.FormaPagamento;
import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.entity.Voucher;
import br.com.cartaoamigo.exception.CorretorInvalidoException;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.exception.VoucherInvalidoException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.rule.VoucherAtivoRule;
import br.com.cartaoamigo.to.AssinaturasTO;
import br.com.cartaoamigo.to.GatewayPagamentoTO;
import br.com.cartaoamigo.to.HistoricoPagamentoTO;
import br.com.cartaoamigo.to.StatusTransacaoGatewayPagamentoTO;
import br.com.cartaoamigo.ws.pagarme.assinatura.AssinaturaPlanoRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.CobrancaFaturaTO;
import br.com.cartaoamigo.ws.pagarme.to.DiscontoTO;
import br.com.cartaoamigo.ws.pagarme.to.FaturaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.ListaFaturasAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.NovaAssinaturaPlanoTO;
import br.com.cartaoamigo.ws.pagarme.to.RetornoAssinaturaPlanoCriadaTO;
import br.com.cartaoamigo.ws.pagarme.to.UltimaCobrancaFaturaTO;


@Component
public class CriarAssinaturaPlanoRecorrenciaPagarmeCmd {
	private static final String PAGARME        = "PAGARME";
	private static final String CARTAO_CREDITO = "CARTAO_CREDITO";
	private static final String BOLETO         = "BOLETO";
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CriarAssinaturaPlanoRecorrenciaPagarmeCmd.class);
	
	@Autowired private AssinaturaPlanoRecorrenciaService service;
	@Autowired private TipoPlanoRepository tipoPlanoRepository;
	@Autowired private VoucherRepository voucherRepository;
	@Autowired private VoucherAtivoRule voucherAtivoRule;
	@Autowired private GetTitularCmd getTitularCmd;
	@Autowired private TitularTOBuilder titularTOBuilder;
	@Autowired private TitularRepository titularRepository;
	@Autowired private CorretorRepository corretorRepository;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetStatusTransacaoCmd getStatusTransacaoCmd;	
	@Autowired private GetGatewayPagamentoCmd getGatewayPagamentoCmd;
	@Autowired private FormaPagamentoRepository formaPagamentoRepository;
	@Autowired private FormaPagamentoTOBuilder formaPagamentoTOBuilder;
	@Autowired private CadastrarHistoricoPagamentoCmd cadastrarHistoricoPagamentoCmd;
	@Autowired private TipoPlanoTOBuilder tipoPlanoTOBuilder;
	@Autowired private GetFaturasAssinaturasPlanoRecorrenciaPagarmeCmd getFaturasAssinaturasPlanoRecorrenciaPagarmeCmd;
	@Autowired private GetCobrancaFaturasAssinaturasPlanoRecorrenciaPagarmeCmd getCobrancaFaturasAssinaturasPlanoRecorrenciaPagarmeCmd;
	@Autowired private GetAssinaturasCmd getAssinaturasCmd;
	@Autowired private SalvarAssinaturaCmd salvarAssinaturaCmd;
	@Autowired private GetAssinaturasPlanoRecorrenciaPagarmeCmd getAssinaturasPlanoRecorrenciaPagarmeCmd;
	
	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaCartao(@RequestBody NovaAssinaturaPlanoTO novaAssinaturaPlanoTO) {
		HistoricoPagamentoTO historicoPagamentoTO = new HistoricoPagamentoTO();
		
		try {
			if(Objects.isNull(novaAssinaturaPlanoTO.getIdPlano())) {
				throw new NotFoundException("Escolha o tipo de plano para realizar o pagamento.");
			}
			
			if(StringUtils.isEmpty(novaAssinaturaPlanoTO.getCustomer_id())) {
				throw new NotFoundException("O código do cliente não foi encontrado.");
			}
			
			Optional<TipoPlano> tipoPlano = tipoPlanoRepository.findById(novaAssinaturaPlanoTO.getIdPlano());
			if(!tipoPlano.isPresent()) {
				throw new NotFoundException("O tipo de plano escolhido não existe: " + novaAssinaturaPlanoTO.getIdPlano());
			}
			
			validarAssinaturaVigente(novaAssinaturaPlanoTO);			
			
			Double valorCobrado = tipoPlano.get().getValor();
			if(valorCobrado < 1.00) {
				throw new PagarmeException("Valor da compra está inferior ao permitido.");
			}
			
			Optional<Voucher> voucher = aplicarVoucher(novaAssinaturaPlanoTO);
			
			Optional<Titular> titular = getTitularCmd.getById(novaAssinaturaPlanoTO.getIdTitular());
			if(!titular.isPresent()) {
				throw new NotFoundException("Titular informado não existe: " + novaAssinaturaPlanoTO.getIdTitular());
			}
			
			preencherDadosCorretor(historicoPagamentoTO, valorCobrado, titular, novaAssinaturaPlanoTO.getCodigoCorretor());
			
			RetornoAssinaturaPlanoCriadaTO retornoAssinaturaTO = null;
			try {
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// Realiza a chamada do endpoint de pagamento do PAGARME
				LOGGER.info(">>>>> Dados pagamento: " + novaAssinaturaPlanoTO.toString());
				
				novaAssinaturaPlanoTO.setPayment_method("credit_card");			
				retornoAssinaturaTO = service.criarAssinaturaCartao(novaAssinaturaPlanoTO);					
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			}catch (Exception e) {
				throw new PagarmeException(e.getMessage());
			}				
			
			historicoPagamentoTO.setId                             (null);
			historicoPagamentoTO.setDtPagamentoPlanoContratado     (LocalDateTime.now());
			historicoPagamentoTO.setLinkPagamento                  (retornoAssinaturaTO.getLinkPagamento());
			historicoPagamentoTO.setNumeroTransacaoGatewayPagamento(retornoAssinaturaTO.getId());
			historicoPagamentoTO.setQtdParcelas                    (retornoAssinaturaTO.getInstallments().longValue());
			historicoPagamentoTO.setTipoMetodoPagamento            (retornoAssinaturaTO.getPayment_method());
			historicoPagamentoTO.setTitular                        (titularTOBuilder.buildTO(titular.get()));	
			
			if(voucher.isPresent()) {
				historicoPagamentoTO.setIdVoucher(voucher.get().getId());
				
				voucher.get().setUtilizado(true);
				voucher.get().setDataUtilizacao(LocalDateTime.now());
				voucher.get().setIdPessoaFisica(titular.get().getPessoaFisica().getId());
				
				voucherRepository.save(voucher.get());
			}			
			
			
			Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findByNome(CARTAO_CREDITO);
			historicoPagamentoTO.setFormaPagamento(formaPagamentoTOBuilder.buildTO(formaPagamento.get()));
			
			historicoPagamentoTO.setTipoPlano(tipoPlanoTOBuilder.buildTO(tipoPlano.get()));
			historicoPagamentoTO.setValorPago(valorCobrado);
			
			//Obtém a cobrança da fatura gerada na assinatura
			CobrancaFaturaTO cobrancaFaturaTO = getCobrancaFatura(novaAssinaturaPlanoTO, retornoAssinaturaTO);

			GatewayPagamentoTO gatewayPagamentoTO = getGatewayPagamentoCmd.getByCodigo(PAGARME);
			StatusTransacaoGatewayPagamentoTO statusTO = getStatusTransacaoCmd.getByStatusAndGateway(cobrancaFaturaTO.getStatus(), gatewayPagamentoTO.getId());

			historicoPagamentoTO.setStatusTransacao    (statusTO);
			historicoPagamentoTO.setGatewayPagamento   (gatewayPagamentoTO);

			historicoPagamentoTO = cadastrarHistoricoPagamentoCmd.cadastrar(historicoPagamentoTO);

			
			//Cria a assinatura na base de dados para o titular
			criarAssinaturaPlano(retornoAssinaturaTO.getId(), tipoPlano.get().getId(), titular.get().getId(), statusTO);
			
			
			retornoAssinaturaTO.setLinkPagamento           (null);
			retornoAssinaturaTO.setStatus                  (statusTO.getCodigoTransacao());
			retornoAssinaturaTO.setDescricaoStatusTransacao(statusTO.getDescricao());
			return retornoAssinaturaTO;		
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao criar a assinatura com cartão: " + e.getMessage());
		}
	}

	private Optional<Voucher> aplicarVoucher(NovaAssinaturaPlanoTO novaAssinaturaPlanoTO) {
		Optional<Voucher> voucher = Optional.empty();
		if(StringUtils.isNotEmpty(novaAssinaturaPlanoTO.getVoucher())) {
			voucher = voucherRepository.findByCodigo(novaAssinaturaPlanoTO.getVoucher());
			if(voucher.isPresent()) {
				voucherAtivoRule.verificar(voucher.get());	
				aplicarDescontos(novaAssinaturaPlanoTO, voucher.get().getPorcentagem());
			} else {
				throw new VoucherInvalidoException("O código de cupom não existe em nossa base de dados."); 
			}
			
		}
		return voucher;
	}

	private void preencherDadosCorretor(HistoricoPagamentoTO historicoPagamentoTO, Double valorCobrado, Optional<Titular> titular, String codigoCorretor) {
		
		if(StringUtils.isNotEmpty(codigoCorretor)){
			titular.get().setCodigoCorretor(codigoCorretor);
			titularRepository.save(titular.get());
		} else {
			codigoCorretor = titular.get().getCodigoCorretor();
		}
		
		//Preenche os dados do corretor
		if(StringUtils.isNotEmpty(codigoCorretor) && valorCobrado > 0) {
			Optional<Corretor> corretor = corretorRepository.findByCodigo(codigoCorretor);
			if(!corretor.isPresent()) {
				throw new NotFoundException("Não existe corretor cadastrado com o código: " + codigoCorretor);
			}
			
			// Não permite que o corretor e o associado seja a mesma pessoa   
			if(corretor.get().getPessoaFisica().getId().equals(titular.get().getId())) {
				throw new CorretorInvalidoException("Não é possível usar o próprio código de corretor no pagamento.");
			}
			
			Double valorCorretor = 0d;
			if(corretor.get().getIsPorcentagem()) {
				valorCorretor = (valorCobrado * corretor.get().getValorRecebimento()) / 100;
			} else {
				valorCorretor = corretor.get().getValorRecebimento();	
			}
			
			historicoPagamentoTO.setCorretor(pessoaFisicaTOBuilder.buildTO(corretor.get().getPessoaFisica()));
			historicoPagamentoTO.setValorCorretor(valorCorretor);
		}
	}

	private void aplicarDescontos(NovaAssinaturaPlanoTO novaAssinaturaPlanoTO, Double porcentagemDesconto) {
		DiscontoTO disconto = new DiscontoTO();
		disconto.setCycles       (12);           //Número de vezes que o desconto será aplicado.
		disconto.setDiscount_type("percentage"); //Valores possíveis: flat ou percentage. Valor padrão: percentage.
		disconto.setStatus       ("active");
		disconto.setValue        (NumeroUtil.formataDoubleComDuasCasasDecimais(porcentagemDesconto));
		
		novaAssinaturaPlanoTO.setDiscounts(Arrays.asList(disconto));
	}

	private void criarAssinaturaPlano(String codigoAssinatura, Long idPlano, Long idTitular, StatusTransacaoGatewayPagamentoTO statusTO) {
		AssinaturasTO to = new AssinaturasTO();
		
		to.setCodigoAssinatura(codigoAssinatura);
		to.setIdPlano(idPlano);
		to.setIdTitular(idTitular);
		
		if(statusTO.getCodigoTransacao().equals("canceled") || statusTO.getCodigoTransacao().equals("failed")) {
			to.setAtivo(false);
		}else {
			to.setAtivo(true);
		}
		
		salvarAssinaturaCmd.salvarAssinatura(to);
	}

	public RetornoAssinaturaPlanoCriadaTO criarAssinaturaBoleto(@RequestBody NovaAssinaturaPlanoTO assinaturaTO) {
		HistoricoPagamentoTO historicoPagamentoTO = new HistoricoPagamentoTO();
		
		try {
			if(Objects.isNull(assinaturaTO.getIdPlano())) {
				throw new NotFoundException("Escolha o tipo de plano para realizar o pagamento.");
			}
			
			if(StringUtils.isEmpty(assinaturaTO.getCustomer_id())) {
				throw new NotFoundException("O código do cliente não foi encontrado.");
			}
			
			Optional<TipoPlano> tipoPlano = tipoPlanoRepository.findById(assinaturaTO.getIdPlano());
			if(!tipoPlano.isPresent()) {
				throw new NotFoundException("O tipo de plano escolhido não existe: " + assinaturaTO.getIdPlano());
			}
			
			Double valorCobrado = tipoPlano.get().getValor();
			if(valorCobrado < 1.00) {
				throw new PagarmeException("Valor da compra está inferior ao permitido.");
			}
			
			validarAssinaturaVigente(assinaturaTO);

			Optional<Voucher> voucher = aplicarVoucher(assinaturaTO);
			
			Optional<Titular> titular = getTitularCmd.getById(assinaturaTO.getIdTitular());
			if(!titular.isPresent()) {
				throw new NotFoundException("Titular informado não existe: " + assinaturaTO.getIdTitular());
			}
			
			preencherDadosCorretor(historicoPagamentoTO, valorCobrado, titular, assinaturaTO.getCodigoCorretor());

			RetornoAssinaturaPlanoCriadaTO retornoAssinaturaTO = null;
			try {
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// Realiza a chamada do endpoint de pagamento do PAGARME
				LOGGER.info(">>>>> Dados pagamento: " + assinaturaTO.toString());
				
				assinaturaTO.setBoleto_due_days(5);
				assinaturaTO.setPayment_method("boleto");
				assinaturaTO.setBank("001");
				retornoAssinaturaTO = service.criarAssinaturaBoleto(assinaturaTO);					
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			}catch (Exception e) {
				throw new PagarmeException(e.getMessage());
			}				

			
			historicoPagamentoTO.setId                             (null);
			historicoPagamentoTO.setDtPagamentoPlanoContratado     (LocalDateTime.now());
			historicoPagamentoTO.setLinkPagamento                  (retornoAssinaturaTO.getLinkPagamento());
			historicoPagamentoTO.setNumeroTransacaoGatewayPagamento(retornoAssinaturaTO.getId());
			historicoPagamentoTO.setQtdParcelas                    (retornoAssinaturaTO.getInstallments().longValue());
			historicoPagamentoTO.setTipoMetodoPagamento            (retornoAssinaturaTO.getPayment_method());
			historicoPagamentoTO.setTitular                        (titularTOBuilder.buildTO(titular.get()));	
			
			if(voucher.isPresent()) {
				historicoPagamentoTO.setIdVoucher(voucher.get().getId());
				
				voucher.get().setUtilizado(true);
				voucher.get().setDataUtilizacao(LocalDateTime.now());
				voucher.get().setIdPessoaFisica(titular.get().getPessoaFisica().getId());
				
				voucherRepository.save(voucher.get());
			}			
			
			Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findByNome(BOLETO);
			historicoPagamentoTO.setFormaPagamento(formaPagamentoTOBuilder.buildTO(formaPagamento.get()));
			
			historicoPagamentoTO.setTipoPlano(tipoPlanoTOBuilder.buildTO(tipoPlano.get()));
			historicoPagamentoTO.setValorPago(valorCobrado);
			
			//Obtém a cobrança da fatura gerada na assinatura
			CobrancaFaturaTO cobrancaFaturaTO = getCobrancaFatura(assinaturaTO, retornoAssinaturaTO);
			
			GatewayPagamentoTO gatewayPagamentoTO = getGatewayPagamentoCmd.getByCodigo(PAGARME);
			StatusTransacaoGatewayPagamentoTO statusTO = getStatusTransacaoCmd.getByStatusAndGateway(cobrancaFaturaTO.getStatus(), gatewayPagamentoTO.getId());

			historicoPagamentoTO.setStatusTransacao        (statusTO);
			historicoPagamentoTO.setGatewayPagamento       (gatewayPagamentoTO);
			
			historicoPagamentoTO.setLinkPagamento          (cobrancaFaturaTO.getLast_transaction().getPdf());			
			historicoPagamentoTO = cadastrarHistoricoPagamentoCmd.cadastrar(historicoPagamentoTO);
			
			//Cria a assinatura na base de dados para o titular
			criarAssinaturaPlano(retornoAssinaturaTO.getId(), tipoPlano.get().getId(), titular.get().getId(), statusTO);

			
			retornoAssinaturaTO.setLinkPagamento           (cobrancaFaturaTO.getLast_transaction().getPdf());
			retornoAssinaturaTO.setStatus                  (statusTO.getCodigoTransacao().toString());
			retornoAssinaturaTO.setDescricaoStatusTransacao(statusTO.getDescricao());
			
			return retornoAssinaturaTO;	
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao criar a assinatura com boleto: " + e.getMessage());
		}
	}

	private void validarAssinaturaVigente(NovaAssinaturaPlanoTO assinaturaTO) {
		AssinaturasTO assinaturaAtiva = getAssinaturasCmd.findAtivaByIdTitular(assinaturaTO.getIdTitular());
		if(Objects.nonNull(assinaturaAtiva)) {
			throw new PagarmeException("Não é possível criar a assinatura, pois já existe uma vigente no momento.");
		}
		
		if(getAssinaturasPlanoRecorrenciaPagarmeCmd.temAssinaturaVigentePagarMe(assinaturaTO.getCustomer_id())) {
			throw new PagarmeException("Cliente já possui assinatura vigente no momento.");
		}
	}

	private CobrancaFaturaTO getCobrancaFatura(NovaAssinaturaPlanoTO assinaturaTO, RetornoAssinaturaPlanoCriadaTO retornoAssinaturaTO) {
		ListaFaturasAssinaturaPlanoTO faturasDaAssinatura = getFaturasAssinaturasPlanoRecorrenciaPagarmeCmd.getFaturasDaAssinatura(assinaturaTO.getCustomer_id(), retornoAssinaturaTO.getId() );
		FaturaAssinaturaPlanoTO faturaTO = faturasDaAssinatura.getData().stream().findFirst().get();
		if(faturaTO.getStatus().equals("paid")) {return new CobrancaFaturaTO(faturaTO.getStatus(), new UltimaCobrancaFaturaTO() );}
		
		return getCobrancaFaturasAssinaturasPlanoRecorrenciaPagarmeCmd.getCobrancaFaturasDaAssinatura(faturaTO.getCharge().getId());
	}
	
	public static void main(String[] args) {
		Double valor = 15.50;
		System.out.println(NumeroUtil.somenteNumeros(NumeroUtil.formataDoubleComDuasCasasDecimais(valor)));
	}
}
