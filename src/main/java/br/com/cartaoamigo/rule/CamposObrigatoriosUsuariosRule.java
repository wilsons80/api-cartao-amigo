package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class CamposObrigatoriosUsuariosRule {

	public void verificar(UsuariosTO to) {
		if(StringUtils.isEmpty(to.getUsername())) {
			throw new CamposObrigatoriosException("Login/CPF deve ser informado.");
		}

		if (Objects.isNull(to.getSenha())) {
			throw new CamposObrigatoriosException("Senha deve ser informada.");
		}

		if(Objects.isNull(to.getPessoaFisica())) {
			throw new CamposObrigatoriosException("Pessoa Física deve ser informada.");
		}	
		
	}
}
