package br.com.cartaoamigo.ws.viacep;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.base.NegocioException;
import br.com.cartaoamigo.to.CepTO;
import br.com.cartaoamigo.ws.HttpRestUtil;

@Component("viaCep")
public class ViaCepWSImpl implements ViaCepWS {

	private static final String VIACEP_URL = "https://viacep.com.br/ws/%s/json";

	@Inject private HttpRestUtil httpRestUtil;

	public CepTO getEndereco(String cep) {
		String url = String.format(VIACEP_URL, cep);
		try {
			return httpRestUtil.get(url, CepTO.class);
		} catch (Exception e) {
			throw new NegocioException("CEP não encontrado");
		}
	}

}
