package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PerfilAcessoUsuarioTOBuilder;
import br.com.cartaoamigo.builder.UsuariosTOBuilder;
import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;

@Component
public class AlterarListaPerfilAcessoUsuarioCmd extends AbstractAlterarListaCmd<PerfilAcessoUsuario, PerfilAcessoUsuarioTO, Usuarios> {

	@Autowired private PerfilAcessoUsuarioRepository repository;
	@Autowired private PerfilAcessoUsuarioTOBuilder toBuilder;
	@Autowired private ExcluirPerfilAcessoUsuarioCmd excluirCmd;
	@Autowired private CadastrarPerfilAcessoUsuarioCmd cadastrarCmd;
	@Autowired private UsuariosTOBuilder usuariosTOBuilder;
	
	@Override
	protected PerfilAcessoUsuarioTO getTO(PerfilAcessoUsuario entity) {
		return toBuilder.buildTO(entity);
	}
	
	@Override
	protected List<PerfilAcessoUsuarioTO> getTOListaBanco(List<PerfilAcessoUsuario> lista) {
		return toBuilder.buildAll(lista);
	}
	
	@Override
	protected List<PerfilAcessoUsuario> getListaBanco(Usuarios pai) {
		return repository.findAllComAcessoByUsuario(pai.getId()).orElse(new ArrayList<>());
	}
	
	@Override
	protected Long getIdentificadorTO(PerfilAcessoUsuarioTO to) {
		return to.getId();
	}
	
	@Override
	protected void cadastrar(PerfilAcessoUsuarioTO to, Usuarios usuario) {
		cadastrarCmd.cadastrar(to, usuariosTOBuilder.buildTO(usuario));
		
	}
	
	@Override
	protected void deletar(PerfilAcessoUsuario registro) {
		excluirCmd.excluir(registro.getId());
	}
	
}
 

                                            
