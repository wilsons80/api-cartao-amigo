package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.DependentesTitularTO;
import br.com.cartaoamigo.to.GrupoAcessoTO;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class AtribuirPermissaoAcessoAssociadoDependenteCmd {
    
	@Autowired private GetGrupoAcessoCmd               getGrupoAcessoCmd;
	@Autowired private CadastrarPerfilAcessoUsuarioCmd cadastrarPerfilAcessoUsuarioCmd;
	
	
	public void atribuir(DependentesTitularTO dependenteTO, UsuariosTO usuarioTO) {
		GrupoAcessoTO grupoAcessoTO = getGrupoAcessoCmd.getGrupoAssociadoDependente();
		if(Objects.isNull(grupoAcessoTO)) {
			throw new NotFoundException("Erro ao recuperar o grupo de acesso do dependente.");
		}
		
		PerfilAcessoUsuarioTO to = new PerfilAcessoUsuarioTO();
		to.setIdUsuario  (usuarioTO.getId());
		to.setGrupoAcesso(grupoAcessoTO);
		
		cadastrarPerfilAcessoUsuarioCmd.cadastrar(to, usuarioTO);
	}

}
