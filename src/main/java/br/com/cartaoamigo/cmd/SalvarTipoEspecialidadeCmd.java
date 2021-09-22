package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TipoEspecialidadeTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoEspecialidadeRepository;
import br.com.cartaoamigo.entity.TipoEspecialidade;
import br.com.cartaoamigo.rule.CampoObrigatorioTipoEspecialiadadeRule;
import br.com.cartaoamigo.to.TipoEspecialidadeTO;

@Component
public class SalvarTipoEspecialidadeCmd {
	
	@Autowired private TipoEspecialidadeRepository repository;
	@Autowired private TipoEspecialidadeTOBuilder toBuilder;
	@Autowired private CampoObrigatorioTipoEspecialiadadeRule rule;
	
	public TipoEspecialidade salvar(TipoEspecialidadeTO to) {
		rule.verificar(to);
		TipoEspecialidade entity = toBuilder.build(to);
		return repository.save(entity);
	}
}
