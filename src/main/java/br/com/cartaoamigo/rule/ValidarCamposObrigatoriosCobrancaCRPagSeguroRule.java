package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;

@Component
public class ValidarCamposObrigatoriosCobrancaCRPagSeguroRule {

	public void validar(String cidade, String endereco, String numero, String bairro, Long cep, String uf) {
		if(StringUtils.isEmpty(cidade)) {
			throw new CamposObrigatoriosException("Cidade do endereço da cobrança deve ser informada.");
		}
		
		if(StringUtils.isEmpty(endereco)) {
			throw new CamposObrigatoriosException("Endereço da cobrança deve ser informada.");
		}

		if(StringUtils.isEmpty(numero)) {
			throw new CamposObrigatoriosException("Número do endereço da cobrança deve ser informada.");
		}
		
		if(StringUtils.isEmpty(bairro)) {
			throw new CamposObrigatoriosException("Bairro do endereço da cobrança deve ser informada.");
		}
		
		if(Objects.isNull(cep)) {
			throw new CamposObrigatoriosException("Cep do endereço da cobrança deve ser informada.");
		}
		
		if(StringUtils.isEmpty(uf)){
			throw new CamposObrigatoriosException("UF do endereço da cobrança deve ser informada.");
		}
	}
}
