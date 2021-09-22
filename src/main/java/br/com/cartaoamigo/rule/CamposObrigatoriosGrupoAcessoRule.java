package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.GrupoAcessoTO;

@Component
public class CamposObrigatoriosGrupoAcessoRule {

	public void verificar(GrupoAcessoTO to) {
		
		if (Objects.isNull(to.getId())) {
			throw new CamposObrigatoriosException("Perfil de acesso deve ser informado.");
		}
	}
}
