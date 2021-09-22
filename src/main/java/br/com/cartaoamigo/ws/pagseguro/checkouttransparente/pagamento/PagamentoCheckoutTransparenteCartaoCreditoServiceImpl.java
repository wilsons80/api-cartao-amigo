package br.com.cartaoamigo.ws.pagseguro.checkouttransparente.pagamento;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.checkouttransparente.PagSeguroProvider;
import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteCartaoCreditoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoPagamentoCheckoutTransparenteCartaoCreditoTO;

@Component
public class PagamentoCheckoutTransparenteCartaoCreditoServiceImpl implements PagamentoCheckoutTransparenteCartaoCreditoService{
	
	@Value("${pagseguro.email}")                                    private String emailPagSeguro;
	@Value("${pagseguro.checkouttransparente.urlNotificationURL}")  private String notificationURL;
	
	@Autowired private PagSeguroProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	
	@Override
	public RetornoPagamentoCheckoutTransparenteCartaoCreditoTO realizarPagamentoCheckoutTransparente(PagamentoCheckoutTransparenteCartaoCreditoTO param) throws Exception {
		HashMap<String, String> parametrosHeader = new HashMap<>();
		parametrosHeader.put("Content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
		map.add("paymentMode"                   , "default");
		map.add("paymentMethod"                 , "creditCard");
		map.add("currency"                      , "BRL");
		
		map.add("notificationURL"               , notificationURL);		
		map.add("receiverEmail"                 , emailPagSeguro );
		
		// Dados da compra (plano escolhido pelo usuário)
		map.add("itemId1"                       , param.getIdPlano());
		map.add("itemDescription1"              , param.getDescricaoPlano());
		map.add("itemAmount1"                   , NumeroUtil.formataNumeroDecimal(param.getValorPlano(), 2));
		map.add("itemQuantity1"                 , "1");

		                                        
		// Dados do comprador
		map.add("senderName"                    , param.getNomeComprador());
		map.add("senderCPF"                     , param.getCpfComprador());
		map.add("senderAreaCode"                , param.getCodAreaComprador()); //DDD
		map.add("senderPhone"                   , param.getTelefoneComprador()); //TELEFONE
		map.add("senderEmail"                   , param.getEmailComprador());

		
		map.add("reference"                     , param.getReference());
		
		//Identificador do comprador (fingerprint) gerado pelo JavaScript do PagSeguro.
		map.add("senderHash"                    , param.getSenderHash());

		map.add("creditCardToken"               , param.getTokenCartaoCredito());
		
		map.add("installmentQuantity"           , param.getQtdParcelas());
		map.add("installmentValue"              , NumeroUtil.formataNumeroDecimal(param.getValorParcela(), 2));
		map.add("noInterestInstallmentQuantity" , param.getQtdParcelasSemJuros());
		
		// Dados do frete
		map.add("shippingAddressRequired"       , false);		

		// Dados do comprador
		map.add("creditCardHolderName"          , param.getNomeImpressoCartao());
		map.add("creditCardHolderCPF"           , param.getCpfTitularCartao());
		map.add("creditCardHolderBirthDate"     , param.getDataNascimentoCartao());
		map.add("creditCardHolderAreaCode"      , param.getCodAreaTitularCartao());
		map.add("creditCardHolderPhone"         , param.getTelefoneTitularCartao());
		
		// Dados do endereço de cobrança
		map.add("billingAddressStreet"          , param.getEnderecoCobranca());
		map.add("billingAddressNumber"          , param.getNumeroCobranca());
		map.add("billingAddressComplement"      , param.getComplementoCobranca());
		map.add("billingAddressDistrict"        , param.getDistritoCobranca());
		map.add("billingAddressPostalCode"      , param.getCodPostalCobranca());
		map.add("billingAddressCity"            , param.getCidadeCobranca());
		map.add("billingAddressState"           , param.getEstadoCobranca());
		map.add("billingAddressCountry"         , "BRA");
		
		
		return httpRestUtil.postForm(pagSeguroProvider.getUrlCheckoutTransparenteCR(), map, RetornoPagamentoCheckoutTransparenteCartaoCreditoTO.class);
	}


}
