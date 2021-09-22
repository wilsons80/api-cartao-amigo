package br.com.cartaoamigo.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoTOBuilder;
import br.com.cartaoamigo.dao.repository.GrupoAcessoRepository;
import br.com.cartaoamigo.dao.repository.PerfilAcessoRepository;
import br.com.cartaoamigo.entity.GrupoAcesso;
import br.com.cartaoamigo.entity.PerfilAcesso;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.rule.CamposObrigatoriosGrupoAcessoRule;
import br.com.cartaoamigo.to.GrupoAcessoTO;

@Component
public class AlterarGrupoAcessoCmd {

	@Autowired private GrupoAcessoRepository repository;
	@Autowired private CamposObrigatoriosGrupoAcessoRule camposObrigatoriosRule;
	@Autowired private GrupoAcessoTOBuilder toBuilder;
	@Autowired private PerfilAcessoRepository perfilAcessoRepository;
	@Autowired private AlterarListaGrupoAcessoModulosCmd alterarListaGrupoAcessoModulosCmd;
	
	public GrupoAcessoTO alterar(GrupoAcessoTO grupoAcesso) {
		camposObrigatoriosRule.verificar(grupoAcesso);

		GrupoAcesso entity = repository.findById(grupoAcesso.getId()).orElseThrow(() -> new NotFoundException("Grupo de acesso informado não existe."));
		entity = toBuilder.build(grupoAcesso);
		entity.setVisivel(true);
		
		PerfilAcesso perfilAcesso = perfilAcessoRepository.findById(grupoAcesso.getPerfilAcesso().getId()).orElseThrow(() -> new NotFoundException("Perfil de Acesso informado não existe."));
		entity.setPerfilAcesso(perfilAcesso);
		entity = repository.save(entity);
		
		alterarListaGrupoAcessoModulosCmd.alterarAll(grupoAcesso.getGruposAcessoModulos(), entity);
		
		return toBuilder.buildTO(repository.save(entity));
	}
}
