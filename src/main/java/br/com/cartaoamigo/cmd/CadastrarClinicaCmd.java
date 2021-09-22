package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicaTOBuilder;
import br.com.cartaoamigo.dao.repository.ClinicaRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.rule.CamposObrigatoriosClinicaRule;
import br.com.cartaoamigo.to.ClinicaTO;

@Component
public class CadastrarClinicaCmd {
	
	@Autowired private ClinicaRepository repository;
	@Autowired private ClinicaTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosClinicaRule rule;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	public ClinicaTO salvar(ClinicaTO to) {
		to.setAtivo(true);
		rule.verificar(to);
		Clinica entity = toBuilder.build(to);
		
		entity.setIdUsuarioApl(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		entity.setDataCadastro(LocalDateTime.now());
		return toBuilder.buildTO( repository.save(entity));
	}
	
}
