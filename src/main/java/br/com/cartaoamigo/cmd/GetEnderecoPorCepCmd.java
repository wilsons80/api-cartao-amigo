package br.com.cartaoamigo.cmd;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.cartaoamigo.rule.CepInvalidoRule;
import br.com.cartaoamigo.to.CepTO;
import br.com.cartaoamigo.ws.viacep.ViaCepWS;

@Component	
public class GetEnderecoPorCepCmd {
	private static final String viacepUrl = "https://viacep.com.br/ws/%s/json";
	
	@Inject private ViaCepWS viaCepWS;
	@Autowired private CepInvalidoRule cepInvalidoRule;

	public CepTO getEndereco(String cep) {
		cepInvalidoRule.validar(cep);
		ResponseEntity<CepTO> responseEntity = getCep(cep.replaceAll("\\D", ""));
		return responseEntity.getBody(); 
	}
	
	public CepTO getEnderecoPorCep(String cep) {
		cepInvalidoRule.validar(cep);
		return viaCepWS.getEndereco(cep.replaceAll("\\D", ""));
	}

	private ResponseEntity<CepTO> getCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		
		String url = String.format(viacepUrl, cep);
		ResponseEntity<CepTO> response  = restTemplate.getForEntity(url, CepTO.class); 
		return response;
	}

}
