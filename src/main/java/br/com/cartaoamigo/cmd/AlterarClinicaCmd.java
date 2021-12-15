package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicaTOBuilder;
import br.com.cartaoamigo.dao.repository.ClinicaRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.rule.CamposObrigatoriosClinicaRule;
import br.com.cartaoamigo.to.ClinicaTO;

@Component
public class AlterarClinicaCmd {

	@Autowired private ClinicaRepository repository;
	@Autowired private ClinicaTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private CamposObrigatoriosClinicaRule rule;
	
	public ClinicaTO alterar(ClinicaTO to) {
		rule.verificar(to);
		
		Clinica entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Clínica informada não existe: " + to.getId()));
		
		entity = toBuilder.build(to);
		entity.setIdUsuarioApl(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		return toBuilder.buildTO(repository.save(entity));
	}

}
