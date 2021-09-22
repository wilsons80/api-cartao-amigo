package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.MedicoEspecialidadesTOBuilder;
import br.com.cartaoamigo.dao.repository.MedicoEspecialidadesRepository;
import br.com.cartaoamigo.entity.Medico;
import br.com.cartaoamigo.entity.MedicoEspecialidades;
import br.com.cartaoamigo.to.MedicoEspecialidadesTO;

@Component
public class AlterarListaMedicoEspecialidadeCmd extends AbstractAlterarListaCmd<MedicoEspecialidades, MedicoEspecialidadesTO, Medico> {

	@Autowired private MedicoEspecialidadesRepository repository;
	@Autowired private MedicoEspecialidadesTOBuilder toBuilder;
	@Autowired private SalvarMedicoEspecialidadesCmd cadastrarCmd;
	
	@Override
	protected MedicoEspecialidadesTO getTO(MedicoEspecialidades entity) {
		return toBuilder.buildTO(entity);
	}
	
	@Override
	protected List<MedicoEspecialidadesTO> getTOListaBanco(List<MedicoEspecialidades> lista) {
		return toBuilder.buildAll(lista);
	}
	
	@Override
	protected List<MedicoEspecialidades> getListaBanco(Medico pai) {
		return repository.findAllByMedico(pai.getId()).orElse(new ArrayList<>());
	}
	
	@Override
	protected Long getIdentificadorTO(MedicoEspecialidadesTO to) {
		return to.getId();
	}
	
	@Override
	protected void cadastrar(MedicoEspecialidadesTO to, Medico medico) {
		cadastrarCmd.cadastrar(to, medico);		
	}
	
	@Override
	protected void deletar(MedicoEspecialidades registro) {
		repository.deleteById(registro.getId());
	}
	
}
 

                                            
