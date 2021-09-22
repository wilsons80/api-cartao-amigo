package br.com.cartaoamigo.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.GrupoAcessoTOBuilder;
import br.com.cartaoamigo.dao.repository.GrupoAcessoRepository;
import br.com.cartaoamigo.entity.GrupoAcesso;
import br.com.cartaoamigo.exception.GruposModuloDuplicadoException;
import br.com.cartaoamigo.to.GrupoAcessoTO;

@Component
public class CadastrarGrupoAcessoCmd {

	@Autowired private GrupoAcessoRepository repository;
	@Autowired private GrupoAcessoTOBuilder toBuilder;
	
	@Autowired private CadastrarGrupoAcessoModulosCmd cadastrarGrupoAcessoModulosCmd;

	public GrupoAcessoTO cadastrar(GrupoAcessoTO grupoAcesso) {
		GrupoAcesso entity = toBuilder.build(grupoAcesso);
		
		Optional<GrupoAcesso> jaExiste = repository.findByNome(grupoAcesso.getNomeGrupoAcesso().toUpperCase());
		if(jaExiste.isPresent()) {
			throw new GruposModuloDuplicadoException("Já existe um grupo de acesso com o nome: " + grupoAcesso.getNomeGrupoAcesso());
		}
		
		entity.setVisivel(true);
		GrupoAcesso grupoAcessoSalvo = repository.save(entity);		
		cadastrarGrupoAcessoModulosCmd.cadastrarAll(grupoAcesso.getGruposAcessoModulos(), grupoAcessoSalvo);
		
		return toBuilder.buildTO(repository.findById(grupoAcessoSalvo.getId()).get());
	}

}
