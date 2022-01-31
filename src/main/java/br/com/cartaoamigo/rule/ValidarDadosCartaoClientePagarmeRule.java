package br.com.cartaoamigo.rule;

import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.infra.util.DataUtil;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.ws.pagarme.to.CriarCartaoClienteTO;

@Component
public class ValidarDadosCartaoClientePagarmeRule {

	public void validar(CriarCartaoClienteTO cartaoTO) {
		if(StringUtils.isEmpty(cartaoTO.getNumber()) || cartaoTO.getNumber().length() < 13 || cartaoTO.getNumber().length() > 19){ 
			throw new CamposObrigatoriosException("Número do cartão inválido.");
		}
		
		validarEdicao(cartaoTO);
	}

	public void validarEdicao(CriarCartaoClienteTO cartaoTO) {
		
		if(StringUtils.isEmpty(cartaoTO.getHolder_name())){ 
			throw new CamposObrigatoriosException("Nome impresso do cartão é obrigatório.");
		}
		if(cartaoTO.getHolder_name().length() > 64){ 
			throw new CamposObrigatoriosException("Nome impresso do cartão deve ter no máximo 64 caracteres.");
		}
		
		String[] nomes = cartaoTO.getHolder_name().split(" ");
		boolean temNumero = Stream.of(nomes).filter(nome -> StringUtil.temNumero(nome) || NumeroUtil.extrairNumerosMatches(nome) > 0).findAny().isPresent();
		if(temNumero) {
			throw new CamposObrigatoriosException("O nome não pode ter números.");
		}
		
		boolean temEspecial = Stream.of(nomes).filter(nome -> !StringUtils.isAlphanumeric(nome)).findAny().isPresent();
		if(temEspecial) {
			throw new CamposObrigatoriosException("O nome não pode caractere especial.");
		}
		
		if(Objects.isNull(cartaoTO.getCustomer())){ 
			throw new CamposObrigatoriosException("O cliente deve ser informado.");
		}
		
		if(StringUtils.isEmpty(cartaoTO.getCustomer().getId())){ 
			throw new CamposObrigatoriosException("O código do cliente deve ser informado.");
		}
		
		if(DataUtil.isDataExpirada(cartaoTO.getExp_month(), cartaoTO.getExp_year())) {
			throw new CamposObrigatoriosException("Cartão está com data vencida.");
		}
	}
		
}
