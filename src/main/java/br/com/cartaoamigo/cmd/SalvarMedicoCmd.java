package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicoTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicosRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.Medico;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.rule.CampoObrigatorioMedicoCrmRule;
import br.com.cartaoamigo.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.cartaoamigo.to.MedicoTO;

@Component
public class SalvarMedicoCmd {
	
	@Autowired private MedicosRepository repository;
	@Autowired private MedicoTOBuilder toBuilder;
	@Autowired private CampoObrigatorioMedicoCrmRule rule;
	@Autowired private CamposObrigatoriosPessoaFisicaRule pessoaFisicaRule;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private SalvarMedicoEspecialidadesCmd salvarMedicoEspecialidadesCmd;
	
	
	public MedicoTO salvar(MedicoTO to) {
		rule.verificar(to);
		
		pessoaFisicaRule.verificar(to.getPessoaFisica());
		Medico entity = toBuilder.build(to);
		
		entity.getPessoaFisica().setDataCadastro(LocalDateTime.now());
		PessoaFisica pessoaFisica = pessoaFisicaRepository.save(entity.getPessoaFisica());
		entity.setPessoaFisica(pessoaFisica);
		
		Medico medico = repository.save(entity);
		
		salvarMedicoEspecialidadesCmd.salvarAll(to.getEspecialidades(), medico.getId());
		
		return toBuilder.buildTO( medico );		
	}
}
