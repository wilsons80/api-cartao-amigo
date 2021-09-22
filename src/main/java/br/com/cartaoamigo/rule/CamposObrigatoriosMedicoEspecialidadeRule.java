package br.com.cartaoamigo.rule;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.CamposObrigatoriosException;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;

@Component
public class CamposObrigatoriosMedicoEspecialidadeRule {

	public void verificar(MedicoEspecialidadesTO to) {
		
		if (Objects.isNull(to.getIdMedico())) {
			throw new CamposObrigatoriosException("Informe o médico.");
		}
		

		if (Objects.isNull(to.getTipoEspecialidade())) {
			throw new CamposObrigatoriosException("Informe o tipo de especialidae.");
		}
	}
}
