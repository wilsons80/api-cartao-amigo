package br.com.cartaoamigo.ws.viacep;

import br.com.cartaoamigo.to.CepTO;

public interface ViaCepWS {
	CepTO getEndereco(String cep);
}
