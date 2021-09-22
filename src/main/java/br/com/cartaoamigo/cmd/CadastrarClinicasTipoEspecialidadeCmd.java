package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ClinicasTipoEspecialidadesTOBuilder;
import br.com.cartaoamigo.builder.TipoEspecialidadeTOBuilder;
import br.com.cartaoamigo.dao.repository.ClinicaRepository;
import br.com.cartaoamigo.dao.repository.ClinicasTipoEspecialidadesRepository;
import br.com.cartaoamigo.entity.Clinica;
import br.com.cartaoamigo.entity.ClinicasTipoEspecialidades;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.ClinicasTipoEspecialidadesTO;

@Component
public class CadastrarClinicasTipoEspecialidadeCmd {

	@Autowired private ClinicasTipoEspecialidadesRepository clinicasTipoEspecialidadesRepository;
	@Autowired private ClinicasTipoEspecialidadesTOBuilder clinicasTipoEspecialidadesTOBuilder;
	@Autowired private ClinicaRepository clinicaRepository;
	@Autowired private TipoEspecialidadeTOBuilder tipoEspecialidadeTOBuilder;

	
	public List<ClinicasTipoEspecialidadesTO> cadastrarAll(List<ClinicasTipoEspecialidadesTO> listaTO) {
		Long idClinica = listaTO.get(0).getClinica().getId();
		Optional<Clinica> clinica = clinicaRepository.findById(idClinica);
		if(!clinica.isPresent()) {
			throw new NotFoundException("Clínica informada não existe.");
		}			
		
		Optional.ofNullable(listaTO).ifPresent(lista -> {
			lista.forEach(to -> cadastrar(to, clinica.get()));
		});
		
		Optional<List<ClinicasTipoEspecialidades>> entitys = clinicasTipoEspecialidadesRepository.findAllByClinica(idClinica);
		return clinicasTipoEspecialidadesTOBuilder.buildAll(entitys.orElse(null));
	}
	
	public void cadastrar(ClinicasTipoEspecialidadesTO to, Clinica clinica) {
		ClinicasTipoEspecialidades entity = new ClinicasTipoEspecialidades();
		entity.setId(to.getId());
		entity.setAtivo(to.getAtivo());
		entity.setClinica(clinica);
		entity.setTipoEspecialidade(tipoEspecialidadeTOBuilder.build(to.getTipoEspecialidade()));
		entity.setValorAssociado(to.getValorAssociado());
		entity.setValorParticular(to.getValorParticular());
		
		clinicasTipoEspecialidadesRepository.save(entity);
	}
	
}
