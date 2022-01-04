package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;

@Component
public class ValidarDadosCartaoClientePagarmeRule {

	public void validar(CriarCartaoClienteTO cartaoTO) {
		if(StringUtils.isEmpty(cartaoTO.getNumber()) || cartaoTO.getNumber().length() < 13 || cartaoTO.getNumber().length() > 19){ 
			throw new CamposObrigatoriosException("Número do cartão inválido.");
		}
		
		if(StringUtils.isEmpty(cartaoTO.getHolder_name())){ 
			throw new CamposObrigatoriosException("Nome impresso do cartão é obrigatório.");
		}
		if(cartaoTO.getHolder_name().length() > 64){ 
			throw new CamposObrigatoriosException("Nome impresso do cartão deve ter no máximo 64 caracteres.");
		}
		
		if(Objects.isNull(cartaoTO.getCustomer())){ 
			throw new CamposObrigatoriosException("O cliente deve ser informado.");
		}
		
		if(StringUtils.isEmpty(cartaoTO.getCustomer().getId())){ 
			throw new CamposObrigatoriosException("O código do cliente deve ser informado.");
		}
	}

}
