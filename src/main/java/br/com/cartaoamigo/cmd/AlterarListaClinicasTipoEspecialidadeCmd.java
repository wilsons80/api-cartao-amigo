package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicasTipoEspecialidadesTOBuilder;
import br.com.cartaoamigo.dao.repository.ClinicasTipoEspecialidadesRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.to.ClinicasTipoEspecialidadesTO;

@Component
public class AlterarListaClinicasTipoEspecialidadeCmd extends AbstractAlterarListaCmd<ClinicasTipoEspecialidades, ClinicasTipoEspecialidadesTO, Clinica> {

	@Autowired private ClinicasTipoEspecialidadesRepository repository;
	@Autowired private ClinicasTipoEspecialidadesTOBuilder toBuilder;
	@Autowired private ExcluirClinicasTipoEspecialidadeCmd excluirCmd;
	@Autowired private CadastrarClinicasTipoEspecialidadeCmd cadastrarCmd;
	
	
	@Override
	protected ClinicasTipoEspecialidadesTO getTO(ClinicasTipoEspecialidades entity) {
		return toBuilder.buildTO(entity);
	}
	
	@Override
	protected List<ClinicasTipoEspecialidadesTO> getTOListaBanco(List<ClinicasTipoEspecialidades> lista) {
		return toBuilder.buildAll(lista);
	}
	
	@Override
	protected List<ClinicasTipoEspecialidades> getListaBanco(Clinica pai) {
		return repository.findAllByClinica(pai.getId()).orElse(new ArrayList<>());
	}
	
	@Override
	protected Long getIdentificadorTO(ClinicasTipoEspecialidadesTO to) {
		return to.getId();
	}
	
	@Override
	protected void cadastrar(ClinicasTipoEspecialidadesTO to, Clinica clinica) {
		cadastrarCmd.cadastrar(to, clinica);
	}
	
	@Override
	protected void deletar(ClinicasTipoEspecialidades registro) {
		excluirCmd.excluir(registro.getId());
	}
	
}
