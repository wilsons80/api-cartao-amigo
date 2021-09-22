package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoModulosTOBuilder;
import br.com.cartaoamigo.dao.repository.GrupoAcessoModulosRepository;
import br.com.cartaoamigo.entity.GrupoAcesso;
import br.com.cartaoamigo.entity.GrupoAcessoModulos;
import br.com.cartaoamigo.to.GrupoAcessoModulosTO;

@Component
public class CadastrarGrupoAcessoModulosCmd {
	
	@Autowired private GrupoAcessoModulosRepository repository;
	@Autowired private GrupoAcessoModulosTOBuilder grupoAcessoModulosTOBuilder;
	@Autowired private GetGrupoAcessoModuloCmd getGrupoAcessoModuloCmd;
	
	
	public void cadastrarModuloPai(GrupoAcessoModulosTO grupoAcessoModulosTO, GrupoAcesso grupoAcessoSalvo) {
		//Verifico se já existe permissão para o módulo pai
		if(Objects.nonNull(grupoAcessoModulosTO.getModulo().getModuloPai()) && Objects.nonNull(grupoAcessoModulosTO.getModulo().getModuloPai().getId()) ) {
			List<GrupoAcessoModulosTO> moduloPai = getGrupoAcessoModuloCmd.getModuloPai(grupoAcessoModulosTO.getIdGrupoAcesso(), grupoAcessoModulosTO.getModulo().getModuloPai().getId());
			if(moduloPai == null || moduloPai.isEmpty()) {
				GrupoAcessoModulosTO acessoModuloPai = new GrupoAcessoModulosTO();
				acessoModuloPai.setId(null);
				acessoModuloPai.setIdGrupoAcesso(grupoAcessoSalvo.getId());
				acessoModuloPai.setModulo(grupoAcessoModulosTO.getModulo().getModuloPai());
				
				cadastrar(acessoModuloPai, grupoAcessoSalvo);
			}	
		}
	}

	
	public void cadastrar(GrupoAcessoModulosTO grupoAcessoModulosTO, GrupoAcesso grupoAcessoSalvo) {
		GrupoAcessoModulos entity = grupoAcessoModulosTOBuilder.build(grupoAcessoModulosTO);
		entity.setIdGrupoAcesso(grupoAcessoSalvo.getId());
		entity = repository.save(entity);
		
		cadastrarModuloPai(grupoAcessoModulosTO, grupoAcessoSalvo);		
	}
	
	public void cadastrarAll(List<GrupoAcessoModulosTO> lista, GrupoAcesso grupoAcessoSalvo) {
		lista.stream().forEach( grupo -> {
			cadastrar(grupo, grupoAcessoSalvo);
		});
	}
}
