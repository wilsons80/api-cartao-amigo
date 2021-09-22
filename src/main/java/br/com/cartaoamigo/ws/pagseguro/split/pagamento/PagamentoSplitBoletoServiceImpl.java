package br.com.cartaoamigo.ws.pagseguro.split.pagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.ws.HttpRestUtil;
import br.com.cartaoamigo.ws.pagseguro.split.PagSeguroSplitProvider;
import br.com.cartaoamigo.ws.pagseguro.to.PagamentoCheckoutTransparenteBoletoTO;
import br.com.cartaoamigo.ws.pagseguro.to.RetornoSplitPagamentoBoletoTO;

@Component
public class PagamentoSplitBoletoServiceImpl implements PagamentoSplitBoletoService{
	
	@Value("${pagseguro.email}")                        private String emailPagSeguro;
	@Value("${pagseguro.split.urlNotificationURL}")     private String notificationURL;
	@Value("${pagseguro.ambiente}")                     private String pagseguroAmbiente;
	
	@Autowired private PagSeguroSplitProvider pagSeguroProvider ;
	@Autowired private HttpRestUtil httpRestUtil;
	
	
	@Override
	public RetornoSplitPagamentoBoletoTO realizarPagamentoCheckoutTransparente(PagamentoCheckoutTransparenteBoletoTO param) throws Exception {
		//HashMap<String, String> parametrosHeader = new HashMap<>();
		//parametrosHeader.put("Content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
		//parametrosHeader.put("Accept"      , "application/vnd.pagseguro.com.br.v3+xml");
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
		map.add("payment.mode"                  , "default");
		map.add("payment.method"                , "boleto");
		map.add("currency"                      , "BRL");
		
		map.add("notificationURL"               , notificationURL);		
		map.add("receiverEmail"                 , emailPagSeguro );
		
		// Dados da compra (plano escolhido pelo usuário)
		map.add("item[1].id"                    , param.getIdPlano());
		map.add("item[1].description"           , param.getDescricaoPlano());
		map.add("item[1].amount"                , NumeroUtil.formataDoubleComDuasCasasDecimais(param.getValorPlano()));
		map.add("item[1].quantity"              , "1");

		map.add("reference"                     , param.getReference());
		
		// Dados do comprador
		
		if(isAmbienteTeste()) {
			map.add("sender.name"                   , "Jose Comprador");
			map.add("sender.CPF"                    , "72962940005");
			map.add("sender.areaCode"               , "11"); //DDD
			map.add("sender.phone"                  , "56273440"); //TELEFONE
			map.add("sender.email"                  , "teste@sandbox.pagseguro.com.br");
			
			//Identificador do comprador (fingerprint) gerado pelo JavaScript do PagSeguro.
			map.add("sender.hash"                   , param.getSenderHash());
			
			// Dados do corretor
			if(param.getIsPorcentagemCorretor()) {
				map.add("receiver[1].publicKey"          , "PUB0481F76D4EB24DF983D18B1C14F97129");
				map.add("receiver[1].split.amount"       , NumeroUtil.formataDoubleComDuasCasasDecimais(param.getValorCorretor()));
			}
			
		} else {
			map.add("sender.name"                   , param.getNomeComprador());
			map.add("sender.CPF"                    , param.getCpfComprador());
			map.add("sender.areaCode"               , param.getCodAreaComprador()); //DDD
			map.add("sender.phone"                  , param.getTelefoneComprador()); //TELEFONE
			map.add("sender.email"                  , param.getEmailComprador());
			
			//Identificador do comprador (fingerprint) gerado pelo JavaScript do PagSeguro.
			map.add("sender.hash"                   , param.getSenderHash());
			
			
			// Dados do corretor
			if(param.getIsPorcentagemCorretor()) {
				map.add("receiver[1].publicKey"          , param.getPublicKeyCorretor());
				map.add("receiver[1].split.amount"       , NumeroUtil.formataDoubleComDuasCasasDecimais(param.getValorCorretor()));
			}
		}

		// Dados do frete
		map.add("shipping.address.required"     , false);		
		
		// Dados do endereço de cobrança
		map.add("billingAddress.street"          , param.getEnderecoCobranca());
		map.add("billingAddress.number"          , param.getNumeroCobranca());
		map.add("billingAddress.complement"      , param.getComplementoCobranca());
		map.add("billingAddress.district"        , param.getDistritoCobranca());
		map.add("billingAddress.postalCode"      , param.getCodPostalCobranca());
		map.add("billingAddress.city"            , param.getCidadeCobranca());
		map.add("billingAddress.state"           , param.getEstadoCobranca());
		map.add("billingAddress.country"         , "BRA");
		

		
		return httpRestUtil.postFormPagSeguro(pagSeguroProvider.getUrlCheckoutTransparenteBoleto(), map, RetornoSplitPagamentoBoletoTO.class);
	}

	
	private boolean isAmbienteTeste() {
		return pagseguroAmbiente.equals("SANDBOX");
	}

}
