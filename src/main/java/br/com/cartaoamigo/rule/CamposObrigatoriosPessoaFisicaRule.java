package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.PessoaFisicaTO;

@Component
public class CamposObrigatoriosPessoaFisicaRule {
	
	public void verificar(PessoaFisicaTO to) {
		if(Objects.isNull(to)) {
			throw new CamposObrigatoriosException("Os dados da pessoa física devem ser informados.");
		}
		
		if(StringUtils.isEmpty(to.getNome())) {
			throw new CamposObrigatoriosException("Nome deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getEmail())) {
			throw new CamposObrigatoriosException("O E-mail deve ser informado.");
		}
		
		if(StringUtils.isEmpty(to.getCidade())) {
			throw new CamposObrigatoriosException("Cidade do endereço deve ser informada.");
		}
		
		if(StringUtils.isEmpty(to.getEndereco())) {
			throw new CamposObrigatoriosException("Endereço deve ser informada.");
		}

		if(StringUtils.isEmpty(to.getNumeroEndereco())) {
			throw new CamposObrigatoriosException("Número do endereço deve ser informada.");
		}
		
		if(StringUtils.isEmpty(to.getBairro())) {
			throw new CamposObrigatoriosException("Bairro do endereço deve ser informada.");
		}
		
		if(Objects.isNull(to.getCep())) {
			throw new CamposObrigatoriosException("Cep do endereço da cobrança deve ser informada.");
		}
		
		if(StringUtils.isEmpty(to.getUf())){
			throw new CamposObrigatoriosException("UF do endereço deve ser informada.");
		}
	}
}
